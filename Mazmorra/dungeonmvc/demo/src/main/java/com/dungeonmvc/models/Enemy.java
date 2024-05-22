package com.dungeonmvc.models;

import java.util.ArrayList;
import java.util.Random;

import com.dungeonmvc.interfaces.Interactive;
import com.dungeonmvc.utils.Vector2;

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
        this.perception = perception;
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

    public void moveRandomly() {
        Direction[] directions = Direction.values();
        Direction randomDirection = directions[random.nextInt(directions.length)];
        move(randomDirection);
    }
    public void interactive() {
        System.out.println("*chilla* tsspsst No pasarás sin pelear conmigo *chilla en rata*");
    }

    public void interactive(String... args) {

    }

}
