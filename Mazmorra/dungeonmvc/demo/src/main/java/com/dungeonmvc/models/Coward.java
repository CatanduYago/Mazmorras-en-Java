package com.dungeonmvc.models;

import java.util.ArrayList;
import java.util.Random;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.utils.DiceRoll;
import com.dungeonmvc.utils.DiceRoll.Dice;
import com.dungeonmvc.utils.Vector2;

public class Coward extends Entities {
    private Board board;
    private Random random;
    private boolean isDead;

    public Coward(String name, String image, Double health, Double AD, Double AP, Double defense, Double speed,
            Vector2 start, Double perception, Board board) {
        super(health, AD, AP, defense, speed, name, image, perception, start);
        this.board = board;
        this.random = new Random();
        this.isDead = false;

    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public void moveAwayFromPlayer(Player player, Coward cowards) {

        if (isWithinDistance(player, this.getPerception())) {
            Direction direction = getDirectionAwayFromPlayer(player);
            if (direction != null) {
                move(direction, player, cowards);
            }
        } else {
            moveRandomly(player, cowards);
        }

    }

    public void move(Direction direction, Player player, Coward cowards) {
        Vector2 destination = getDestination(position, direction);
        if (destination.getX() >= 0 && destination.getX() < board.getSize() && destination.getY() >= 0
                && destination.getY() < board.getSize()) {
            Cell cell = board.getCell(destination);
            boolean isCellOccupied = GameManager.getInstance().getEnemies().stream().anyMatch(e -> e.getPosition().equals(destination));
            if ((cell.getIsFloor() || cell.getIsDoor()) && !isCellOccupied) {
                setPosition(destination);
                board.notifyObservers();
            }
        }
    }

    private void moveRandomly(Player player, Coward cowards) {
        Direction[] directions = Direction.values();
        Direction direction = directions[random.nextInt(directions.length)];
        move(direction, player, cowards);
    }

    private boolean isWithinDistance(Player player, Double perception) {
        int dx = Math.abs(player.getPosition().getX() - this.getPosition().getX());
        int dy = Math.abs(player.getPosition().getY() - this.getPosition().getY());
        return dx + dy <= perception;
    }

    private Direction getDirectionAwayFromPlayer(Player player) {
        int dx = player.getPosition().getX() - this.getPosition().getX();
        int dy = player.getPosition().getY() - this.getPosition().getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? Direction.LEFT : Direction.RIGHT;
        } else {
            return dy > 0 ? Direction.UP : Direction.DOWN;
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
    public void receiveDamage(double damage) {
        this.setHealth(this.getHealth() - damage);
        System.out.println(this.getName() + " recibe " + damage + " de daño. Vida restante: " + this.getHealth());
        if (this.getHealth() <= 0) {
            System.out.println(this.getName() + " ha sido derrotado.");
            this.isDead = true;
            

        }
    }
    @Override
    public void interactive(String... args) {
        // Implementar si es necesario
    }

}
