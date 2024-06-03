package com.dungeonmvc.controllers;
import com.dungeonmvc.App;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Board;
import com.dungeonmvc.models.Chest;
import com.dungeonmvc.models.Entities;
import com.dungeonmvc.models.Enemy;
import com.dungeonmvc.models.Player;
import com.dungeonmvc.utils.Vector2;
import com.dungeonmvc.utils.Vector2Double;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardViewController implements Observer {
    @FXML
    private Pane pane;
    @FXML
    private GridPane grid;
    private Board board;
    private double cellSize;
    private double boardSize;

    private HashMap<Entities, ImageView> entityImages;
 private HashMap<Chest, ImageView> chestImages;
    private static BoardViewController instance;

    public BoardViewController() {
        instance = this;
    }

    public static BoardViewController getInstance() {
        return instance;
    }

    @FXML
    private void initialize() {
        System.out.println("Board controller loaded");
    }
    public void setUp() {
        board = GameManager.getInstance().getBoard();
        board.subscribe(this);
        int cellNumber = board.getSize();
        cellSize = boardSize / cellNumber;
        System.out.println(cellSize);
        for (int i = 0; i < cellNumber; i++) {
            grid.addRow(i);
            grid.addColumn(i);
        }
        for (int row = 0; row < cellNumber; row++) {
            for (int col = 0; col < cellNumber; col++) {
                ImageView boardImg = new ImageView();
                boardImg.setFitWidth(cellSize);
                boardImg.setFitHeight(cellSize);
                boardImg.setSmooth(false);
                if (board.isFloor(row, col)) {
                    boardImg.setImage(new Image(
                            App.class.getResource("images/" + board.getFloorImage() + ".png").toExternalForm(),
                            cellSize, cellSize, true, false));
                } else {
                    boardImg.setImage(
                            new Image(App.class.getResource("images/" + board.getWallImage() + ".png").toExternalForm(),
                                    cellSize, cellSize, true, false));
                }
                grid.add(boardImg, row, col);
            }
        }

        entityImages = new HashMap<>();

        Player player = GameManager.getInstance().getPlayer();
        ImageView playerImg = new ImageView();
        playerImg.setFitWidth(cellSize);
        playerImg.setFitHeight(cellSize);
        playerImg.setImage(new Image(App.class.getResource("images/" + player.getImage() + ".png").toExternalForm(), cellSize, cellSize, true, false));
        playerImg.setSmooth(false);
        pane.getChildren().add(playerImg);
        entityImages.put(player, playerImg);

        for (Enemy enemy : GameManager.getInstance().getEnemies()) {
            ImageView enemyImg = new ImageView();
            enemyImg.setFitWidth(cellSize);
            enemyImg.setFitHeight(cellSize);
            enemyImg.setImage(new Image(App.class.getResource("images/" + enemy.getImage() + ".png").toExternalForm(), cellSize, cellSize, true, false));
            enemyImg.setSmooth(false);
            pane.getChildren().add(enemyImg);
            entityImages.put(enemy, enemyImg);
        }

         chestImages = new HashMap<>();
        for (Chest chest : GameManager.getInstance().getChests()) {
            ImageView chestImg = new ImageView();
            chestImg.setFitWidth(cellSize);
            chestImg.setFitHeight(cellSize);
            chestImg.setImage(new Image(App.class.getResource("images/" + chest.getImage() + ".png").toExternalForm(), cellSize, cellSize, true, false));
            chestImg.setSmooth(false);
            pane.getChildren().add(chestImg);
            chestImages.put(chest, chestImg);
        }

        onChange();
    }

    @Override
    public void onChange() {
        for (Entities entity : entityImages.keySet()) {
            Vector2Double newPos = matrixToInterface(entity.getPosition());
            ImageView imgView = entityImages.get(entity);
            imgView.setLayoutX(newPos.getX());
            imgView.setLayoutY(newPos.getY());
        }

        for (Chest chest : chestImages.keySet()) {
            Vector2Double newPos = matrixToInterface(chest.getPosition());
            ImageView imgView = chestImages.get(chest);
            imgView.setLayoutX(newPos.getX());
            imgView.setLayoutY(newPos.getY());
            imgView.setImage(new Image(App.class.getResource("images/" + chest.getImage() + ".png").toExternalForm(), cellSize, cellSize, true, false));
        }
    }

    public void updateChestImage(Chest chest) {
        ImageView chestImg = chestImages.get(chest);
        if (chestImg != null) {
            chestImg.setImage(new Image(App.class.getResource("images/openchest.png").toExternalForm(), cellSize, cellSize, true, false));
        }
    }

    @Override
    public void onChange(String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }
    private Vector2Double matrixToInterface(Vector2 coord) {
        return new Vector2Double(cellSize * coord.getX(), cellSize * coord.getY());
    }
    public void setBoardSize(double boardSize) {
        this.boardSize = boardSize;
    }

    public void updateEnemyImage(Enemy enemy) {
        ImageView enemyImg = entityImages.get(enemy);
        if (enemyImg != null) {
            enemyImg.setImage(new Image(App.class.getResource("images/deadrat.png").toExternalForm(), cellSize, cellSize, true, false));
        }
    }

    public void updatePlayerImage(Player player) {
        ImageView playerImg = entityImages.get(player);
        if (playerImg != null) {
            playerImg.setImage(new Image(App.class.getResource("images/playerdead.png").toExternalForm(), cellSize, cellSize, true, false));
        }
    }
}