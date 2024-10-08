package com.gross.simulation;

public class Constants {
//Для заполнения двумерного массива
    public static final int CREATURE = 0;
    public static final int GRASS = -2;
    public static final int STATIC_ENTITY = -3;
    public static final int HERBIVORE = -1;
    public static final int PREDATOR = -5;
    public static final int EMPTY = -4;


    private Constants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}