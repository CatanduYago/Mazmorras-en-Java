package com.dungeonmvc.models;

import java.util.ArrayList;

import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.utils.Vector2;

public class Player {
    ArrayList<Observer> observers;

    String portrait;
    String image;
    String name;
    String leftHand;
    String rightHand;
    Vector2 position;

    public Player(String portrait, String image, String name, String leftHand, String rightHand, Vector2 start) {
        observers = new ArrayList<>();

        this.portrait = portrait;
        this.image = image;
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.position = start;
    }

    public void suscribe(Observer observer){
        observers.add(observer);
    }

    public void unsuscribe(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        observers.forEach(x -> x.onChange());   
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
        notifyObservers();
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
        this.name = name;
        notifyObservers();
    }

    public String getLeftHand() {
        return this.leftHand;
    }

    public void setLeftHand(String leftHand) {
        this.leftHand = leftHand;
        notifyObservers();
    }

    public String getRightHand() {
        return this.rightHand;
    }

    public void setRightHand(String rightHand) {
        this.rightHand = rightHand;
        notifyObservers();
    }

    public ArrayList<Observer> getObservers() {
        return this.observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public int getX(){
        return this.position.getX();
    }

    public int getY(){
        return this.position.getY();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        //notifyObservers();
    }
}
