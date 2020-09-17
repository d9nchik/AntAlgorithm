package com.d9nich.antAlgorithm;

public class Ant {
    private final double[][] PHEROMONE;
    private final double[][] MATRIX_OF_REVERSE_DISTANCE;
    private final int startPoint;

    public Ant(double[][] PHEROMONE, double[][] MATRIX_OF_REVERSE_DISTANCE, int startPoint) {
        this.PHEROMONE = PHEROMONE;
        this.MATRIX_OF_REVERSE_DISTANCE = MATRIX_OF_REVERSE_DISTANCE;
        this.startPoint = startPoint;
    }
}
