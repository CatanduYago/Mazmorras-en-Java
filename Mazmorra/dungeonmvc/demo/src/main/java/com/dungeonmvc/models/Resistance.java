package com.dungeonmvc.models;

public class Resistance {
    public enum Type {
        LETAL, RESISTENTE, FRAGIL, IRROMPIBLE, VULNERABLE, ABSORBENTE, NEUTRAL, INMUNE
    }

    private Type type;

    public Resistance(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public double modifyDamage(double damage) {
        switch (type) {
            case LETAL:
                return Double.MAX_VALUE; 
            case RESISTENTE:
                return damage / 2;
            case FRAGIL:
                return damage * 4;
            case IRROMPIBLE:
                return damage / 4;
            case VULNERABLE:
                return damage * 2;
            case ABSORBENTE:
                return -damage; 
            case NEUTRAL:
                return damage;
            case INMUNE:
                return 0;
            default:
                return damage;
        }
    }
}