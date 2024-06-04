package com.dungeonmvc.controllers;

import com.dungeonmvc.App;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Inventory;
import com.dungeonmvc.models.Player;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import com.dungeonmvc.models.Consumable;

public class InventoryViewController implements Observer {

    @FXML
    GridPane grid;

    private Inventory inventory;
    private final int cellSize = 50;
    private final int itemsPerRow = 4;

    private ContextMenu menu;
    private int selectedItemIndex = -1;

    @FXML
    private void initialize() {
        System.out.println("Inventory controller loaded");

        Player player = GameManager.getInstance().getPlayer();
        inventory = player.getInventory();
        inventory.suscribe(this);

        menu = new ContextMenu();

        MenuItem equipIZQ = new MenuItem("Equipar Izquierda");
        MenuItem equipDER = new MenuItem("Equipar Derecha");
        MenuItem consume = new MenuItem("Consumir");
        MenuItem delete = new MenuItem("Tirar");

        equipIZQ.setOnAction(e -> equipItem("izquierda"));
        equipDER.setOnAction(e -> equipItem("derecha"));
        consume.setOnAction(e -> consumeItem());
        delete.setOnAction(e -> deleteItem());

        menu.getItems().addAll(equipIZQ, equipDER, consume, delete);

        updateInventoryView();
    }

    public void updateInventoryView() {
        grid.getChildren().clear();

        for (int i = 0; i < inventory.getInventorySize(); i++) {
            ImageView itemImgView = new ImageView();
            itemImgView.setFitWidth(cellSize);
            itemImgView.setFitHeight(cellSize);
            itemImgView.setSmooth(false);
            itemImgView.setPickOnBounds(true);
            itemImgView.setViewport(new Rectangle2D(0, 0, cellSize, cellSize));
            itemImgView.setImage(new Image(App.class.getResource("images/" + inventory.getItem(i) + ".png").toExternalForm(), cellSize, cellSize, true, false));
            final int index = i;
            itemImgView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    selectedItemIndex = index;
                    showMenu(event, itemImgView);
                }
            });
            grid.add(itemImgView, i % itemsPerRow, i / itemsPerRow);
        }
    }

    private void showMenu(MouseEvent event, ImageView imageView) {
        menu.hide();
        menu.show(imageView, event.getScreenX(), event.getScreenY());
    }

    private void equipItem(String hand) {
        if (selectedItemIndex != -1) {
            Player player = GameManager.getInstance().getPlayer();
            String selectedItem = inventory.getItem(selectedItemIndex);
            inventory.removeItem(selectedItemIndex);

            if (hand.equals("izquierda")) {
                if (player.getLeftHand() != null && !player.getLeftHand().isEmpty()) {
                    inventory.addItem(player.getLeftHand());
                }
                player.setLeftHand(selectedItem);
            } else if (hand.equals("derecha")) {
                if (player.getRightHand() != null && !player.getRightHand().isEmpty()) {
                    inventory.addItem(player.getRightHand());
                }
                player.setRightHand(selectedItem);
            }

            player.notifyObservers();
            System.out.println("Objeto equipado en " + hand + ": " + selectedItem);
            updateInventoryView();
        }
    }


    public void consumeItem() {
        if (selectedItemIndex != -1) {
            Player player = GameManager.getInstance().getPlayer();
            String selectedItem = inventory.getItem(selectedItemIndex);
            Consumable consumableItem = new Consumable(selectedItem, selectedItem, cellSize); 
            inventory.removeItem(selectedItemIndex);
            player.consume(consumableItem);
            updateInventoryView();
        }
    }

    public void deleteItem() {
        if (selectedItemIndex != -1) {
            String selectedItem = inventory.getItem(selectedItemIndex);
            inventory.removeItem(selectedItemIndex);
            System.out.println("Objeto eliminado: " + selectedItem);
            updateInventoryView();
        }
    }

    @Override
    public void onChange() {
        updateInventoryView();
    }

    @Override
    public void onChange(String... args) {
        updateInventoryView();
    }
}
