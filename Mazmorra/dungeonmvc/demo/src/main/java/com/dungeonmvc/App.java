package com.dungeonmvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.dungeonmvc.models.Boss;
import com.dungeonmvc.models.Item;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        setupScene(stage);
        showStage(stage);
    }

    private void setupScene(Stage stage) throws IOException {
        stage.setResizable(false);
        GameManager gm = GameManager.getInstance();
        gm.testGame();
        scene = new Scene(loadFXML("mainView"));
        stage.setScene(scene);
        stage.setTitle("Java y mazmorras");
        stage.getIcons().add(new Image(App.class.getResource("images/logo.png").toExternalForm()));
            Boss boss = new Boss("Ecutron", "boss.png", 100.0, 10.0, 0.0, 5.0, 1.0, 1.0);
        boss.addItem(new Item("Escultura maldita", "escultura.png", 10.0, 3.0, 0.0, 0.0, 0.0));
        boss.addItem(new Item("Espada maldita", "espada.png", 0.0, 5.0, 0.0, 0.0, 0.0));
        boss.addItem(new Item("Armadura de Río", "armadura.png", 50.0, 0.0, 0.0, 20.0, 0.0));
        boss.addItem(new Item("Poción de Salud", "pocion.png", 25.0, 0.0, 0.0, 0.0, 0.0));

        ArrayList<Item> droppedItems = boss.dropItems();
    }

    private void showStage(Stage stage) {
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
