package com.gross.simulation.entity.staticEntity;

import com.gross.simulation.entity.Entity;

public class Grass extends Entity {
    private final String icon="🌱";

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int health=50;
    public String getIcon() {
        return icon;
    }
}
