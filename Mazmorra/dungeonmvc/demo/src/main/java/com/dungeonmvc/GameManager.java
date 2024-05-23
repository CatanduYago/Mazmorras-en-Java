package com.dungeonmvc;

import com.dungeonmvc.models.*;
import com.dungeonmvc.models.Player.Direction;
import com.dungeonmvc.utils.Vector2;

public class GameManager {
    private static GameManager instance;

    Player player;
    Enemy enemy;
    Board board;

    private GameManager() {
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

    public Enemy getEnemy() {
        return this.enemy;
    }

    public Board getBoard() {
        return this.board;
    }

    public void newTurn(Direction direction) {
        
            player.move(direction);
            enemy.moveTowardsPlayer(player);
            if (player.getPosition().equals(enemy.getPosition())) {
                player.interact(enemy);
            }
            

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
                { true, false, false, false, true, false, false, false, false, false, false, false, false, false,
                        true },
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

        player = new Player("portrait", "player", "Paladin", 5.0, 1.3, 0.0, 1.0, 1.0, 1.0, "item7", "item6",
                new Vector2(4, 0), board);
        enemy = new Enemy("Roedor del Río", "rata", 10.0, 1.0, 0.0, 1.0, 1.0, new Vector2(0, 0), 3.0,board);

        player.getInventory().addItem("item1");
        player.getInventory().addItem("item2");
        player.getInventory().addItem("item3");
        player.getInventory().addItem("item4");
        player.getInventory().addItem("item5");
        player.getInventory().addItem("item8");
    }
}
