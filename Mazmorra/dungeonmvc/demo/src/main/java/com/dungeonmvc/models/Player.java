package com.dungeonmvc.models;

import java.util.ArrayList;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.utils.DiceRoll;
import com.dungeonmvc.utils.DiceRoll.Dice;
import com.dungeonmvc.utils.Vector2;

public class Player extends Entities {
    private ArrayList<Observer> observers;
    private String portrait;
    private String leftHand;
    private String rightHand;
    private Vector2 position;
    private Inventory inventory;
    private ArrayList<String> resistencias;
    private Board board;
    private Double maxHealth;

    public Player(String portrait, String image, String name, Double health, Double AD, Double AP, Double defense,
            Double speed, Double perception, String leftHand, String rightHand, Vector2 start, Board board) {
        super(health, AD, AP, defense, speed, name, image, perception);
        observers = new ArrayList<>();
        this.maxHealth = health;
        this.portrait = portrait;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.position = start;
        this.inventory = new Inventory();
        this.resistencias = new ArrayList<>();
        this.board = board;
    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.forEach(x -> x.onChange());
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

    public Vector2 getPosition() {
        return this.position;
    }
    public Double getMaxHealth() {
        return this.maxHealth;
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void move(Direction direction) {
        Vector2 destination = getDestination(position, direction);
        if (destination.getX() >= 0 && destination.getX() < board.getSize() && destination.getY() >= 0
                && destination.getY() < board.getSize()) {
            Cell cell = board.getCell(destination);
            if (cell.getIsFloor() || cell.getIsDoor()) {
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
    
    public void attackEnemy(Enemy enemy) {
        double damageDice = DiceRoll.roll(Dice.d6);
        double damage = this.getAD() + damageDice - enemy.getDefense();
        if (damage > 0) {
            enemy.receiveDamage(damage);
            System.out.println(this.getName() + " ataca haciendo " + damage + " de daño a " + enemy.getName());
        } else {
            System.out.println(this.getName() + " ataca pero no le hace daño a " + enemy.getName());
        }
    }

    public void receiveDamage(double damage) {
        this.setHealth(this.getHealth() - damage);
        System.out.println(this.getName() + " recibe " + damage + " de daño. Vida restante: " + this.getHealth());
        if (this.getHealth() <= 0) {
            System.out.println(this.getName() + " ha sido derrotado.");
        }
        notifyObservers();
    }

    
}
