package com.dungeonmvc.models;

import java.util.ArrayList;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Player.Direction;
import com.dungeonmvc.utils.Vector2;

public class Board {
    private ArrayList<Observer> observers;
    private int size;
    private Cell[][] board;
    private String floorImage;
    private String wallImage;
    private String doorImage;
    boolean[][] visibility;

    public Board(int size, String floorImage, String wallImage, String doorImage) {
        this.size = size;
        this.board = new Cell[size][size];
        this.floorImage = floorImage;
        this.wallImage = wallImage;
        this.doorImage = doorImage;
        observers = new ArrayList<>();
        this.visibility = new boolean[size][size];
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.forEach(x -> x.onChange());
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFloorImage() {
        return this.floorImage;
    }

    public void setFloorImage(String floorImage) {
        this.floorImage = floorImage;
    }

    public String getWallImage() {
        return this.wallImage;
    }

    public void setWallImage(String wallImage) {
        this.wallImage = wallImage;
    }

    public String getDoorImage() {
        return this.doorImage;
    }

    public void setDoorImage(String doorImage) {
        this.doorImage = doorImage;
    }

    public boolean isFloor(Vector2 position) {
        return board[position.getX()][position.getY()].getIsFloor();
    }

    public boolean isFloor(int x, int y) {
        return board[x][y].getIsFloor();
    }
    public boolean isVisible(int row, int col) {
        return visibility[row][col];
    }

    public void updateVisibility(Vector2 playerPosition, double perception) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                visibility[i][j] = false;
            }
        }

        int range = (int) Math.round(perception);
        for (int dx = -range; dx <= range; dx++) {
            for (int dy = -range; dy <= range; dy++) {
                int x = playerPosition.getX() + dx;
                int y = playerPosition.getY() + dy;
                if (x >= 0 && x < size && y >= 0 && y < size) {
                    visibility[x][y] = true;
                }
            }
        }
    }
    public ArrayList<Observer> getObservers() {
        return this.observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }


    public Cell getCell(Vector2 position) {
        return board[position.getX()][position.getY()];
    }

    public void newCell(Vector2 position, boolean isFloor) {
        Cell cell = new Cell(isFloor);
        board[position.getX()][position.getY()] = cell;
    }

    public void newCell(Vector2 position, boolean isFloor, boolean isDoor) {
        Cell cell = new Cell(isFloor, isDoor);
        board[position.getX()][position.getY()] = cell;
    }

    
}
