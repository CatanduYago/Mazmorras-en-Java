package com.dungeonmvc.models;

public class Consumable extends Item {
    private double effect; 

    public Consumable(String name, String description, double effect) {
        super(name, description);
        this.effect = effect;
    }

    public double getEffect() {
        return effect;
    }
    public void setEffect(double effect) {
        this.effect = effect;
    }

    @Override
    public void use(Player player) {
        player.consume(this);
    }
}
