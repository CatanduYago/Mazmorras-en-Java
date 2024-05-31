package com.dungeonmvc.models;

public class Artifact extends Item {
    private double effectPower;

    public Artifact(String name, String description, double effectPower) {
        super(name, description);
        this.effectPower = effectPower;
    }

    public double getEffectPower() {
        return effectPower;
    }

    @Override
    public void use(Player player) {
        player.useArtifact(this);
    }
}
