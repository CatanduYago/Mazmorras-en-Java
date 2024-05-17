package com.dungeonmvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.dungeonmvc.models.Board;
import com.dungeonmvc.models.Cell;
import com.dungeonmvc.utils.Vector2;

import java.io.IOException;

public class App extends Application {

    private static final int WINDOW_WIDTH = 800; // Ancho de la ventana
    private static final int WINDOW_HEIGHT = 600; // Alto de la ventana

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(true);
        GameManager gm = GameManager.getInstance();
        gm.testGame();

        GridPane gridPane = new GridPane();
        Board board = gm.getBoard();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Cell cell = board.getCell(new Vector2(i, j));
                ImageView imageView;

                if (cell.hasDoor()) {
                    imageView = new ImageView(new Image(App.class.getResource("images/door.png").toExternalForm()));
                } else if (cell.getIsFloor()) {
                    imageView = new ImageView(new Image(App.class.getResource("images/floor.png").toExternalForm()));
                } else {
                    imageView = new ImageView(new Image(App.class.getResource("images/wall.png").toExternalForm()));
                }

                gridPane.add(imageView, j, i);
            }
        }


        Scene scene = new Scene(gridPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Java y Mazmorras");
        stage.getIcons().add(new Image(App.class.getResource("images/logo.png").toExternalForm()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}
