package com.dungeonmvc.models;

public class Weapon extends Item {
    private double attackBonus;
    private double defenseBonus;

    public Weapon(String name, String description, double attackBonus, double defenseBonus) {
        super(name, description);
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
    }

    public double getAttackBonus() {
        return attackBonus;
    }

    public double getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public void use(Player player) {
        player.equipWeapon(this);
    }
}
