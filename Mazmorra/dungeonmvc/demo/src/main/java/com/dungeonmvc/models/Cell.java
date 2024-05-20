package com.dungeonmvc.models;

import com.dungeonmvc.interfaces.Interactive;

public class Cell {
    private boolean isFloor;
    private boolean isDoor;
    private Interactive interactor;
    public Cell(boolean isFloor) {
        this.isFloor = isFloor;
        this.isDoor = false;
    }

    public Cell(boolean isFloor, boolean isDoor) {
        this.isFloor = isFloor;
        this.isDoor = isDoor;
    }

    public boolean getIsFloor() {
        return isFloor;
    }

    public boolean getIsDoor() {
        return isDoor;
    }

    public void setDoor(boolean isDoor) {
        this.isDoor = isDoor;
    }

    public Interactive getInteractor() {
        return interactor;
    }

    public void setInteractor(Interactive interactor) {
        this.interactor = interactor;
    }
}
