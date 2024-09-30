package com.gross.simulation.entity.creatures.herbivores;
public class Cow extends Herbivore {
    private final String icon = "🐮";

    public String getIcon() {
        return icon;
    }

    public Cow() {
        super(30, 1);
    }


}
