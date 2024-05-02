package com.dungeonmvc.controllers;

import com.dungeonmvc.interfaces.Observer;

import javafx.fxml.FXML;

public class InventoryViewController implements Observer{
    @FXML
    private void initialize() {
        System.out.println("Inventory controller loaded");
    }

    @Override
    public void onChange() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }

    @Override
    public void onChange(String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }
}
