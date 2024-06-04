package com.dungeonmvc.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.controllers.InventoryViewController;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.utils.DiceRoll;
import com.dungeonmvc.utils.DiceRoll.Dice;
import com.dungeonmvc.utils.Vector2;

public class Player extends Entities {
    private ArrayList<Observer> observers;
    private String portrait;
    private String leftHand;
    private String rightHand;
    private Inventory inventory;
    private HashMap<Skill.Type, Resistance> resistances;
    private Board board;
    private double maxHealth;
    private boolean isDead;
    private Weapon equippedWeapon;

    public Player(String portrait, String image, String name, double health, double AD, double AP, double defense,
            double speed, double perception, String leftHand, String rightHand, Vector2 start, Board board) {
        super(health, AD, AP, defense, speed, name, image, perception, start);
        observers = new ArrayList<>();
        this.portrait = portrait;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.maxHealth = health;
        this.inventory = new Inventory();
        this.resistances = new HashMap<>();
        this.board = board;
        this.isDead = false;
    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public boolean isDead() {
        return isDead;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.forEach(Observer::onChange);
    }

    public Double getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(Double maxHealth) {
        this.maxHealth = health;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
        notifyObservers();
    }

    public String getLeftHand() {
        return this.leftHand;
    }

    public void setLeftHand(String leftHand) {
        this.leftHand = leftHand;
        notifyObservers();
    }

    public String getRightHand() {
        return this.rightHand;
    }

    public void setRightHand(String rightHand) {
        this.rightHand = rightHand;
        notifyObservers();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setResistance(Skill.Type skillType, Resistance resistance) {
        resistances.put(skillType, resistance);
    }

    public double modifyDamage(Skill.Type skillType, double damage) {
        Resistance resistance = resistances.get(skillType);
        return resistance != null ? resistance.modifyDamage(damage) : damage;
    }

    public void move(Direction direction) {
        if (isDead)
            return;

        Vector2 destination = getDestination(position, direction);
        if (destination.getX() >= 0 && destination.getX() < board.getSize() && destination.getY() >= 0
                && destination.getY() < board.getSize()) {
            Cell cell = board.getCell(destination);
            boolean isCellOccupied = GameManager.getInstance().getEnemies().stream()
                    .anyMatch(e -> e.getPosition().equals(destination));
            if ((cell.getIsFloor() || cell.getIsDoor()) && !isCellOccupied) {
                setPosition(destination);
                board.notifyObservers();
            }
        }
    }

    private Vector2 getDestination(Vector2 position, Direction direction) {
        int destX = position.getX();
        int destY = position.getY();
        switch (direction) {
            case UP:
                destY--;
                break;
            case RIGHT:
                destX++;
                break;
            case DOWN:
                destY++;
                break;
            case LEFT:
                destX--;
                break;
        }
        return new Vector2(destX, destY);
    }

    public void interact(Enemy enemy) {
        if (this.getSpeed() > enemy.getSpeed()) {
            attackEnemy(enemy);
            if (enemy.getHealth() > 0) {
                enemy.attackPlayer(this);
            }
        } else {
            enemy.attackPlayer(this);
            if (this.getHealth() > 0) {
                attackEnemy(enemy);
            }
        }
    }

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        System.out.println(this.getName() + " ha equipado " + weapon.getName());
        notifyObservers();
    }

    private void attackEnemy(Enemy enemy) {
        double damageDice = DiceRoll.roll(Dice.d6);
        double weaponDamage = (equippedWeapon != null) ? equippedWeapon.getAttackBonus() : 0;
        double baseDamage = this.getAD() + weaponDamage + damageDice;

        if (equippedWeapon != null) {
            for (Skill skill : equippedWeapon.getSkills()) {
                double damage = enemy.modifyDamage(skill.getType(), baseDamage);
                if (damage > 0) {
                    enemy.receiveDamage(damage);
                    System.out.println(this.getName() + " ataca haciendo " + damage + " de daño " +
                            "con habilidad " + skill.getType() + " a " + enemy.getName());
                } else {
                    System.out.println(this.getName() + " ataca pero no le hace daño " +
                            "con habilidad " + skill.getType() + " a " + enemy.getName());
                }
            }
        } else {
            enemy.receiveDamage(baseDamage);
            System.out.println(this.getName() + " ataca haciendo " + baseDamage + " de daño a " + enemy.getName());
        }
    }

    public void receiveDamage(double damage) {
        this.setHealth(this.getHealth() - damage);
        System.out.println(this.getName() + " recibe " + damage + " de daño. Vida restante: " + this.getHealth());
        notifyObservers();
        if (this.getHealth() <= 0) {
            System.out.println(this.getName() + " ha sido derrotado.");
            GameManager.getInstance().notifyPlayerDefeated(this);
            GameManager.getInstance().checkGameOver();

        }
    }

    public void useArtifact(Artifact artifact) {
        // TODO: Implementar uso de artefactos
    }

    public void consume(Consumable consumable) {
        String itemName = consumable.getName().toLowerCase();

        switch (itemName) {
            case "coconut":
                double effect = 5.0;
                this.setHealth(Math.min(this.getHealth() + effect, this.maxHealth));
                System.out.println(this.getName() + " consume un " + itemName + " y recupera " + effect + " de vida.");
                break;
            case "shit":
                double effectcaca = 5.0;
                this.setHealth(this.getHealth() - effectcaca);
                System.out.println(this.getName() + " consume " + itemName + " y pierde " + effectcaca + " de vida.");
                break;
            case "syringe":
                double effectsyringe = 15.0;

                this.setHealth(Math.min(this.getHealth() + effectsyringe, this.maxHealth));
                System.out.println(
                        this.getName() + " consume un " + itemName + " y recupera " + effectsyringe + " de vida.");
                break;
            default:
                System.out.println(this.getName() + " consume un " + itemName + " sin efecto.");
                break;
        }
        notifyObservers();

    }

}
