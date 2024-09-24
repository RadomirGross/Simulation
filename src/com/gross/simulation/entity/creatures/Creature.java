package com.gross.simulation.entity.creatures;

import com.gross.simulation.entity.Entity;
import com.gross.simulation.map.GameMap;

public abstract class Creature extends Entity {
    private int health;
    private int speed;


    public abstract void makeMove(GameMap gameMap,int startX,int startY);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    pu
}
