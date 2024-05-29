package com.dungeonmvc.models;

import java.util.ArrayList;
import java.util.Random;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Interactive;
import com.dungeonmvc.utils.DiceRoll;
import com.dungeonmvc.utils.DiceRoll.Dice;
import com.dungeonmvc.utils.Vector2;

import javafx.stage.Stage;

public class Enemy extends Entities implements Interactive {

    Double perception;
    String image;
    String name;
    Vector2 position;
    private Board board;
     private Random random;


    public Enemy(String name, String image, Double health, Double AD, Double AP, Double defense, Double speed,
            Vector2 start,
            Double perception, Board board) {
        super(health, AD, AP, defense, speed);
        this.name = name;
        this.image = image;
        this.health = health;
        this.AD = AD;
        this.AP = AP;
        this.defense = defense;
        this.speed = speed;
        this.position = start;
        this.perception = perception;
        this.board = board;
        this.random = new Random(); 

    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPerception() {
        return this.perception;
    }

    public void setPerception(Double perception) {
        this.perception = 3.0;
    }

    public Vector2 getPosition() {
        return this.position;
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

    public void moveTowardsPlayer(Player player) {
        if (isWithinDistance(player, perception)) {
            Direction direction = getDirectionTowardsPlayer(player);
            if (direction != null) {
                move(direction);
                
            }
        } else {
            moveRandomly();
        }
    }

    private boolean isWithinDistance(Player player, Double perception) {
        int dx = Math.abs(player.getX() - this.getX());
        int dy = Math.abs(player.getY() - this.getY());
        return dx + dy <= perception;
    }

    private Direction getDirectionTowardsPlayer(Player player) {
        int dx = player.getX() - this.getX();
        int dy = player.getY() - this.getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? Direction.RIGHT : Direction.LEFT;
        } else {
            return dy > 0 ? Direction.DOWN : Direction.UP;
        }
    }

    public void attackPlayer(Player player) {
        double damageDice = DiceRoll.roll(Dice.d6);
        double damage = this.getAD() + damageDice - player.getDefense();
        if (damage > 0) {
            player.receiveDamage(damage);
            System.out.println(this.getName() + " ataca haciendo " + damage + " de daño a " + player.getName());
        } else {
            System.out.println(this.getName() + " ataca pero no le hace daño a " + player.getName());
        }
    }

    public void receiveDamage(double damage) {
        this.setHealth(this.getHealth() - damage);
        System.out.println(this.getName() + " recibe " + damage + " de daño. Vida restante: " + this.getHealth());
        if (this.getHealth() <= 0) {
            System.out.println(this.getName() + " ha sido derrotado.");
        }
    }

    public void move(Direction direction) {
        Vector2 destination = getDestination(position, direction);
        if (destination.getX() >= 0 && destination.getX() < board.getSize() && destination.getY() >= 0
                && destination.getY() < board.getSize()) {
            Cell cell = board.getCell(destination);
            if (cell.getIsFloor() || cell.getIsDoor()) {
                setPosition(destination);
                board.notifyObservers();
                if (this.position.getY() == GameManager.getInstance().getPlayer().getPosition().getY()) {
                    attackPlayer(GameManager.getInstance().getPlayer());
                }
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

    public void moveRandomly() {
        Direction[] directions = Direction.values();
        Direction direction = directions[random.nextInt(directions.length)];
        move(direction);
    }
    

    public void interactive(String... args) {

    }

}
