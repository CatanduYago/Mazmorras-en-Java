package com.dungeonmvc.models;

import com.dungeonmvc.interfaces.Interactive;
import com.dungeonmvc.utils.Vector2;

public abstract class Entities implements Interactive {
    Double health;
    Double AD;
    Double AP;
    Double defense;
    Double speed;
    String name;
    String image;
    Double perception;
    Vector2 position;

    public Entities(Double health, Double AD, Double AP, Double defense, Double speed, String name, String image, Double perception, Vector2 position) {
        this.health = health;
        this.AD = AD;
        this.AP = AP;
        this.defense = defense;
        this.speed = speed;
        this.name = name;
        this.image = image;
        this.perception = perception;
        this.position = position;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
        if (health <= 0) {
            System.out.println(this.getName() + " ha sido derrotado.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAD() {
        return this.AD;
    }

    public void setAD(Double AD) {
        this.AD = AD;
    }

    public Double getAP() {
        return this.AP;
    }

    public void setAP(Double AP) {
        this.AP = AP;
    }

    public Double getDefense() {
        return this.defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }

    public Double getSpeed() {
        return this.speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPerception() {
        return this.perception;
    }

    public void setPerception(Double perception) {
        this.perception = perception;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public void interactive() {
        // Implementar si es necesario
    }

    @Override
    public void interactive(String... args) {
        // Implementar si es necesario
    }
}
