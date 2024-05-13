package com.dungeonmvc.models;

public class Character {

    String name;
    Double health;
    Double AD;
    Double defense;
    Double speed;
    Double perception;

    public Character(String name, Double health, Double AD, Double defense, Double speed, Double perception) {
        this.name = name;
        this.health = health;
        this.AD = AD;
        this.defense = defense;
        this.speed = speed;
        this.perception = perception;
    }
}