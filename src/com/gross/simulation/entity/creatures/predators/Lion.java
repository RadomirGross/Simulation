package com.gross.simulation.entity.creatures.predators;

public class Lion extends Predator {
    private final String icon = "🦁";

    public Lion() {
        super(100, 5, 20);
    }

    public String getIcon() {
        return icon;
    }
}
