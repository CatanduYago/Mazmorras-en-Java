package com.dungeonmvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static App instance;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        instance = this;
        stage.setResizable(false);
        GameManager gm = GameManager.getInstance();
        gm.testGame();
        scene = new Scene(loadFXML("mainView"));
        stage.setScene(scene);
        stage.setTitle("Java y mazmorras");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static App getInstance() {
        return instance;
    }

    public void showGameOver() {
        System.out.println("Game Over");
        restartGame();
    }

    public void restartGame() {
        try {
            stage.close();
            start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
