package com.gross.simulation.entity.creatures.predators;

public class Bear extends Predator {
    private final String icon="🐻";
public Bear() {
    super(100,4,30);
}
    public String getIcon() {
        return icon;
    }
}
