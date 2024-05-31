package com.dungeonmvc.models;

public class Consumable extends Item {
    private double healthBonus;

    public Consumable(String name, String description, double healthBonus) {
        super(name, description);
        this.healthBonus = healthBonus;
    }

    public double getHealthBonus() {
        return healthBonus;
    }

    @Override
    public void use(Player player) {
        player.consume(this);
    }
}
