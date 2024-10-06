package com.gross.simulation.entity.creatures.predators;

public class Wolf extends Predator {
    private final String icon = "🐺";

    public Wolf() {
        super(50, 3, 10);
    }

    public String getIcon() {
        return icon;
    }
}
