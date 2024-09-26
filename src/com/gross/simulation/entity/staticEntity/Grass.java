package com.gross.simulation.entity.staticEntity;

import com.gross.simulation.entity.Entity;

public class Grass extends StaticEntity {
    private final String icon="🌱";

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int health=10;
    public String getIcon() {
        return icon;
    }
}
