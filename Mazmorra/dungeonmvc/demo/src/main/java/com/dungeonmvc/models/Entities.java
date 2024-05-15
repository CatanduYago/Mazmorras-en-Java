package com.dungeonmvc.models;

import com.dungeonmvc.interfaces.Interactive;

public class Entities implements Interactive{
    Double health;

    Double AD;
    Double AP;
    Double defense;
    Double speed;

    public Entities(Double health, Double AD, Double AP, Double defense, Double speed) {
        this.health = health;
        this.AD = AD;
        this.AP = AP;
        this.defense = defense;
        this.speed = speed;
        }
    
    public Double getHealth() {
        return this.health;
    }
    public void setHealth(Double health) {
        this.health = health;
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

    public void interactive() {

    }

    public void interactive(String... args) {
        
    }


    
    

}