package com.dungeonmvc.controllers;

import com.dungeonmvc.App;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Board;
import com.dungeonmvc.models.Enemy;
import com.dungeonmvc.models.Player;
import com.dungeonmvc.utils.Vector2;
import com.dungeonmvc.utils.Vector2Double;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardViewController implements Observer {
    @FXML
    private Pane pane;
    @FXML
    private GridPane grid;

    private Board board;
    private double cellSize;
    private double boardSize;
    ImageView enemyImg;

    private ImageView playerImg;

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

        playerImg = new ImageView();
        enemyImg = new ImageView();

        playerImg.setFitWidth(cellSize);
        playerImg.setFitHeight(cellSize);
        playerImg.setImage(new Image(
                App.class.getResource("images/" + GameManager.getInstance().getPlayer().getImage() + ".png")
                        .toExternalForm(),cellSize, cellSize, true, false));
        playerImg.setSmooth(false);
        pane.getChildren().add(playerImg);

        enemyImg.setFitWidth(cellSize);
        enemyImg.setFitHeight(cellSize);
        enemyImg.setImage(new Image(
                App.class.getResource("images/" + GameManager.getInstance().getEnemy().getImage() + ".png")
                        .toExternalForm(),cellSize, cellSize, true, false));
        enemyImg.setSmooth(false);
        pane.getChildren().add(enemyImg);
        onChange();

    }

    @Override
    public void onChange() {
        Vector2Double newPos = matrixToInterface(GameManager.getInstance().getPlayer().getPosition());
        Vector2Double newPosEnemy = matrixToInterface(GameManager.getInstance().getEnemy().getPosition());

        System.out.println(newPos);
        playerImg.setLayoutX(newPos.getX());
        playerImg.setLayoutY(newPos.getY());
        System.out.println(newPosEnemy);
        enemyImg.setLayoutX(newPosEnemy.getX());
        enemyImg.setLayoutY(newPosEnemy.getY());
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
}
