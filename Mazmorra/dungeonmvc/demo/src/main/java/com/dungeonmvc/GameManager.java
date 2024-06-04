package com.dungeonmvc;

import com.dungeonmvc.controllers.BoardViewController;
import com.dungeonmvc.models.*;
import com.dungeonmvc.models.Player.Direction;
import com.dungeonmvc.utils.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<Chest> getChests() {
        return this.chests;
    }

    public Board getBoard() {
        return this.board;
    }

    public void newTurn(Direction direction) {
        player.move(direction);
        BoardViewController.getInstance().onChange();
        for (Enemy enemy : new ArrayList<>(enemies)) {
            if (enemy.getHealth() > 0) {
                enemy.moveTowardsPlayer(player, enemies);
                if (areAdjacent(player.getPosition(), enemy.getPosition())) {
                    player.interact(enemy);
                }
            }
            if (enemy.getHealth() <= 0) {
                enemies.remove(enemy);
                if (enemies.isEmpty()) {
                    App.getInstance().restartGame();
                }
            }
        }

        for (Chest chest : chests) {
            if (areAdjacent(player.getPosition(), chest.getPosition())) {
                chest.open(player);
        }}
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
        player = new Player("portrait", "player", "HunterHansolo", 25.0, 1.3, 0.0, 1.0, 1.0, 1.0, "dpickaxe", "item6",
                new Vector2(4, 0), board);
        player.setResistance(Skill.Type.CORTANTE,new Resistance(Resistance.Type.RESISTENTE));
        
        Weapon dpickaxe = new Weapon("dpickaxe", "El pico del Minecraft", 5.0, 2.0, Arrays.asList(new Skill(Skill.Type.CONTUNDENTE)));
        player.equipWeapon(dpickaxe);

        Enemy rata1 = new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(0, 0), 3.0, board);
        rata1.setResistance(Skill.Type.CORTANTE, new Resistance(Resistance.Type.RESISTENTE));
        enemies.add(rata1);

        Enemy rata2 = new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(2, 4), 3.0, board);
        rata2.setResistance(Skill.Type.CORTANTE, new Resistance(Resistance.Type.ABSORBENTE));
        enemies.add(rata2);

        Enemy rata3 = new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(10, 4), 3.0, board);
        rata3.setResistance(Skill.Type.CORTANTE, new Resistance(Resistance.Type.NEUTRAL));
        enemies.add(rata3);


        Enemy rata4 = new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(8, 4), 3.0, board);
        rata4.setResistance(Skill.Type.CORTANTE, new Resistance(Resistance.Type.INMUNE));
        enemies.add(rata4);

        Enemy rata5 = new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(0, 12), 3.0, board);
        rata5.setResistance(Skill.Type.CORTANTE, new Resistance(Resistance.Type.FRAGIL));
        enemies.add(rata5);

        player.getInventory().addItem("coconut");
        player.getInventory().addItem("shit");
        player.getInventory().addItem("syringe");
        player.getInventory().addItem("item1");
        player.getInventory().addItem("item2");
        player.getInventory().addItem("item3");
        player.getInventory().addItem("dl44");
        
        chests.add(new Chest(new Vector2(0, 2), "chest", "openchest"));
        chests.add(new Chest(new Vector2(10, 14), "chest", "openchest"));

   
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
