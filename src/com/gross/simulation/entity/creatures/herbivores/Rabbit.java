package com.gross.simulation.entity.creatures.herbivores;
public class Rabbit extends Herbivore {
    private final String icon = "🐰";

    public String getIcon() {
        return icon;
    }

    public Rabbit() {
        super(10, 2);
    }


}
