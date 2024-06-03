package com.dungeonmvc;

import com.dungeonmvc.controllers.BoardViewController;
import com.dungeonmvc.models.*;
import com.dungeonmvc.models.Player.Direction;
import com.dungeonmvc.utils.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class GameManager {
    private static GameManager instance;

    Player player;
    ArrayList<Enemy> enemies;
    ArrayList<Chest> chests;

    Board board;

    private GameManager() {
        enemies = new ArrayList<>();
        chests = new ArrayList<>();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public Board getBoard() {
        return this.board;
    }

    public ArrayList<Chest> getChests() {
        return chests;
    }

    public void newTurn(Direction direction) {
        player.move(direction);

        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            if (enemy.getHealth() > 0) {
                enemy.moveTowardsPlayer(player, enemies);
                if (areAdjacent(player.getPosition(), enemy.getPosition())) {
                    player.interact(enemy);
                }
            }
            if (enemy.getHealth() <= 0) {
                enemyIterator.remove();
            }
        }

        for (Chest chest : chests) {
            if (areAdjacent(player.getPosition(), chest.getPosition())) {
                chest.open(player);
            }
        }
    }

    private boolean areAdjacent(Vector2 pos1, Vector2 pos2) {
        int dx = Math.abs(pos1.getX() - pos2.getX());
        int dy = Math.abs(pos1.getY() - pos2.getY());
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    public void testGame() {
        boolean[][] boardMatrix = {
                { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
                { true, false, false, false, true, false, false, false, false, false, false, false, false, false,
                        true },
                { true, false, true, false, true, false, true, true, true, true, true, true, false, true, true },
                { true, false, true, false, true, false, false, false, false, false, false, false, false, false, true },
                { true, false, true, false, true, false, true, true, true, true, true, true, false, true, true },
                { true, false, false, false, true, false, false, false, false, false, false, false, false, false,
                        true },
                { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
                { true, false, false, false, true, false, false, false, false, false, false, false, false, false,
                        true },
                { true, false, true, false, true, false, true, true, true, true, true, true, false, true, true },
                { true, false, true, false, true, false, false, false, false, false, false, false, false, false, true },
                { true, false, true, false, true, false, true, true, true, true, true, true, false, true, true },
                { true, false, false, false, true, false, false, false, false, false, false, false, false, false,true },
                { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true }
        };

        board = new Board(boardMatrix.length, "floor", "wall", "door");
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[0].length; j++) {
                boolean isFloor = boardMatrix[i][j];
                boolean isDoor = (i == 1 && j == 4) || (i == 4 && j == 8);
                board.newCell(new Vector2(i, j), isFloor, isDoor);
            }
        }

        player = new Player("portrait", "player", "Paladin", 25.0, 1.3, 0.0, 1.0, 1.0, 1.0, "dpickaxe", "item6",
                new Vector2(4, 0), board);

        chests.add(new Chest(new Vector2(0, 2), "chest", "openchest"));
        chests.add(new Chest(new Vector2(10, 14), "chest", "openchest"));

        enemies.add(new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(0, 0), 3.0, board));
        enemies.add(new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(2, 4), 3.0, board));

        player.getInventory().addItem("coconut");
        player.getInventory().addItem("shit");
        player.getInventory().addItem("syringe");
        player.getInventory().addItem("item1");
        player.getInventory().addItem("item2");
        player.getInventory().addItem("item3");
        player.getInventory().addItem("item4");

    }

    public void notifyEnemyDefeated(Enemy enemy) {
        BoardViewController.getInstance().updateEnemyImage(enemy);
    }

    public void notifyPlayerDefeated(Player player) {
        BoardViewController.getInstance().updatePlayerImage(player);

    }
public void notifyChestOpened(Chest chest) {
        BoardViewController.getInstance().updateChestImage(chest);
    }
    public void checkGameOver() {
        if (player.getHealth() <= 0) {
            App.getInstance().showGameOver();

        }
    }
}
