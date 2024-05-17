package com.dungeonmvc.models;

import com.dungeonmvc.interfaces.Interactive;

public class Cell {
    private boolean isFloor;
    private boolean hasDoor;
    private Interactive interactor;

    public Cell(boolean isFloor) {
        this.isFloor = isFloor;
        this.hasDoor = false; 
        this.interactor = null; 
    }
    

    public boolean getIsFloor() {
        return isFloor;
    }

    public void setIsFloor(boolean isFloor) {
        this.isFloor = isFloor;
    }

    public boolean hasDoor() {
        return hasDoor;
    }

    public void setHasDoor(boolean hasDoor) {
        this.hasDoor = hasDoor;
    }

    public Interactive getInteractor() {
        return interactor;
    }

    public void setInteractor(Interactive interactor) {
        this.interactor = interactor;
    }
}
