package com.dungeonmvc.models;

import java.util.ArrayList;
import java.util.Random;

import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Item;
import com.dungeonmvc.utils.Vector2;

public class Boss extends Enemy {
    private ArrayList<Item> items;

    public Boss(String name, String image, Double health, Double AD, Double AP, Double defense, Double speed, Vector2 position,
            Double perception, Board board) {
        super(name, image, health, AD, AP, defense, speed, position, perception, board);
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Item> dropItems() {
        ArrayList<Item> droppedItems = new ArrayList<>();
        
        droppedItems.addAll(items);
        
        items.clear();

        return droppedItems;
    }

    public ArrayList<Item> dropRandomItems(int numItemsToDrop) {
        ArrayList<Item> droppedItems = new ArrayList<>();

        if (items.size() <= numItemsToDrop) {
            droppedItems.addAll(items);
            items.clear();
        } else {
            Random random = new Random();
            for (int i = 0; i < numItemsToDrop; i++) {
                int index = random.nextInt(items.size());
                Item item = items.remove(index);
                droppedItems.add(item);
            }
        }

        return droppedItems;


}}
