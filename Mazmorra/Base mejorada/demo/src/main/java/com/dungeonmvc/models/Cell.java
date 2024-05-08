package com.dungeonmvc.models;

public class Cell {
    private boolean isFloor;

    public Cell(boolean isFloor) {
        this.isFloor = isFloor;
    }

    public boolean isIsFloor() {
        return this.isFloor;
    }

    public boolean getIsFloor() {
        return this.isFloor;
    }

    public void setIsFloor(boolean isFloor) {
        this.isFloor = isFloor;
    }

}
