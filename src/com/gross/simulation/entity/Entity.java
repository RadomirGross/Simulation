package com.gross.simulation.entity;

public abstract class Entity
{
    protected Coordinate position;

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public abstract String getIcon();
}
