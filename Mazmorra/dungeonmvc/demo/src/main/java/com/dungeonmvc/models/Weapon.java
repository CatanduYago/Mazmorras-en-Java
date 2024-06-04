package com.dungeonmvc.models;

import java.util.List;

public class Weapon extends Item {
    private double attackBonus;
    private double defenseBonus;
    private List<Skill> skills;

    public Weapon(String name, String description, double attackBonus, double defenseBonus, List<Skill> skills) {
        super(name, description);
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.skills = skills;
    }

    public double getAttackBonus() {
        return attackBonus;
    }

    public double getDefenseBonus() {
        return defenseBonus;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public void use(Player player) {
        player.equipWeapon(this);
    }
}
