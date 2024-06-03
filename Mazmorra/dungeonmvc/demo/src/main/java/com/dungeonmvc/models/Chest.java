package com.dungeonmvc.models;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.utils.Vector2;

import java.util.Random;

public class Chest {
    private Vector2 position;
    private boolean isOpen;
    private String closedImage;
    private String openImage;

    public Chest(Vector2 position, String closedImage, String openImage) {
        this.position = position;
        this.isOpen = false;
        this.closedImage = closedImage;
        this.openImage = openImage;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getImage() {
        return isOpen ? openImage : closedImage;
    }

    public void open(Player player) {
        if (!isOpen) {
            isOpen = true;
            String[] items = {"coconut", "shit", "syringe", "item1", "item2", "item3", "item4"};
            Random random = new Random();
            String randomItem = items[random.nextInt(items.length)];
            player.getInventory().addItem(randomItem);
            System.out.println("Chest opened! Added " + randomItem + " to inventory.");
            GameManager.getInstance().notifyChestOpened(this);
        }
    }
}
