package com.dungeonmvc.controllers;

import java.io.IOException;

import com.dungeonmvc.App;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.models.Player.Direction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainViewController {
    @FXML
    Button restartButton;
    @FXML
    Pane inventoryPane;
    @FXML
    Pane playerPane;
    @FXML
    Pane boardPane;

    @FXML
    private void initialize() {
        try {
            FXMLLoader boardLoader = new FXMLLoader(App.class.getResource("boardView.fxml"));
            Pane boardView = boardLoader.load();
            boardPane.getChildren().add(boardView);
            boardView.prefWidthProperty().bind(boardPane.widthProperty());
            boardView.prefHeightProperty().bind(boardPane.heightProperty());
            BoardViewController bvc = boardLoader.getController();
            bvc.setBoardSize(boardPane.getPrefHeight());
            bvc.setUp();

            FXMLLoader inventoryLoader = new FXMLLoader(App.class.getResource("inventoryView.fxml"));
            Pane inventoryView = inventoryLoader.load();
            inventoryPane.getChildren().add(inventoryView);

            FXMLLoader playerLoader = new FXMLLoader(App.class.getResource("playerView.fxml"));
            Pane playerView = playerLoader.load();
            playerPane.getChildren().add(playerView);

        
           

            boardPane.setOnMouseClicked(event -> boardPane.requestFocus());

            boardPane.setOnKeyPressed(event -> {
                Direction direction = null;
                if (event.getCode() == KeyCode.UP) {
                    System.out.println("Tecla arriba presionada");
                    direction = Direction.UP;
                } else if (event.getCode() == KeyCode.DOWN) {
                    System.out.println("Tecla abajo presionada");
                    direction = Direction.DOWN;
                } else if (event.getCode() == KeyCode.LEFT) {
                    System.out.println("Tecla izquierda presionada");
                    direction = Direction.LEFT;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    System.out.println("Tecla derecha presionada");
                    direction = Direction.RIGHT;
                }
                if (direction != null) {
                    GameManager.getInstance().newTurn(direction);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initializeAfterReset() {
        boardPane.requestFocus();
    }
}
