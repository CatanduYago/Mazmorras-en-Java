package com.dungeonmvc.models;

public class Skill {
    public enum Type {
        CONTUNDENTE, PERFORANTE, CORTANTE, FUEGO, MAGIA, ABRIR
    }

    private Type type;

    public Skill(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}