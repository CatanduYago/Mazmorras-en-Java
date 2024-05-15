package com.dungeonmvc.models;

import java.util.ArrayList;

import com.dungeonmvc.interfaces.Interactive;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.utils.Vector2;

public class Enemies extends Entities implements Interactive {

    Double perception;
    String image;
    String name;

    public Enemies(String name, String image, Double health, Double AD, Double AP, Double defense, Double speed,
            Double perception) {
        super(health, AD, AP, defense, speed);
        this.name = name;
        this.image = image;
        this.health = health;
        this.AD = AD;
        this.AP = AP;
        this.defense = defense;
        this.speed = speed;
        this.perception = perception;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = "Limpiador del Río";
    }

    public Double getPerception() {
        return this.perception;
    }

    public void setPerception(Double perception) {
        this.perception = perception;
    }

    public void interactive() {
        System.out.println("*chilla* tsspsst No pasarás sin pelear conmigo *chilla en rata*");
    }

    public void interactive(String... args) {



    }

}
