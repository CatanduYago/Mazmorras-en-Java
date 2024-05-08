package com.dungeonmvc.models;

import com.dungeonmvc.utils.Vector2;

public class Enemy extends Player{

    public Enemy(String portrait, String image, String name, Double health, Double AD, Double AP, Double defense,
            Double speed, Double perception, String leftHand, String rightHand, Vector2 start) {
        super(portrait, image, name, health, AD, AP, defense, speed, perception, leftHand, rightHand, start);
    }

}
