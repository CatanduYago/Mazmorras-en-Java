package com.dungeonmvc.models;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.utils.DiceRoll;
import com.dungeonmvc.utils.DiceRoll.Dice;
import com.dungeonmvc.utils.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entities {
    private Board board;
    private Random random;
    private boolean isDead;

    public Enemy(String name, String image, Double health, Double AD, Double AP, Double defense, Double speed,
            Vector2 start, Double perception, Board board) {
        super(health, AD, AP, defense, speed, name, image, perception, start);
        this.board = board;
        this.random = new Random();
        this.isDead = false;
    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public boolean isDead() {
        return isDead;
    }

    public void moveTowardsPlayer(Player player, ArrayList<Enemy> enemies) {
        if (isDead)
            return;

        if (isWithinDistance(player, this.getPerception())) {
            Direction direction = getDirectionTowardsPlayer(player);
            if (direction != null) {
                move(direction, player, enemies);
            }
        } else {
            moveRandomly(player, enemies);
        }
    }

    public void move(Direction direction, Player player, ArrayList<Enemy> enemies) {
        Vector2 destination = getDestination(position, direction);
        if (destination.getX() >= 0 && destination.getX() < board.getSize() && destination.getY() >= 0
                && destination.getY() < board.getSize()) {
            Cell cell = board.getCell(destination);
            boolean isCellOccupied = player.getPosition().equals(destination)
                    || enemies.stream().anyMatch(e -> e.getPosition().equals(destination));
            if ((cell.getIsFloor() || cell.getIsDoor()) && !isCellOccupied) {
                setPosition(destination);
                board.notifyObservers();
            }
        }
    }

    private void moveRandomly(Player player, ArrayList<Enemy> enemies) {
        Direction[] directions = Direction.values();
        Direction direction = directions[random.nextInt(directions.length)];
        move(direction, player, enemies);
    }

    private boolean isWithinDistance(Player player, Double perception) {
        int dx = Math.abs(player.getPosition().getX() - this.getPosition().getX());
        int dy = Math.abs(player.getPosition().getY() - this.getPosition().getY());
        return dx + dy <= perception;
    }

    private Direction getDirectionTowardsPlayer(Player player) {
        int dx = player.getPosition().getX() - this.getPosition().getX();
        int dy = player.getPosition().getY() - this.getPosition().getY();
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
            this.isDead = true;
            this.setImage("deadrat");
            GameManager.getInstance().notifyEnemyDefeated(this);

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

    @Override
    public void interactive(String... args) {
        // Implementar si es necesario
    }
}
