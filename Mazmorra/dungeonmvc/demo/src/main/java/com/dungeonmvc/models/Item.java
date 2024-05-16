package com.dungeonmvc.models;

public class Item {
    private String name;
    private String image;
    private Double health;
    private Double AD;
    private Double AP;
    private Double defense;
    private Double speed;

    public Item(String name, String image, Double health, Double AD, Double AP, Double defense, Double speed) {
        this.name = name;
        this.image = image;
        this.health = health;
        this.AD = AD;
        this.AP = AP;
        this.defense = defense;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getAD() {
        return AD;
    }

    public void setAD(Double AD) {
        this.AD = AD;
    }

    public Double getAP() {
        return AP;
    }

    public void setAP(Double AP) {
        this.AP = AP;
    }

    public Double getDefense() {
        return defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
