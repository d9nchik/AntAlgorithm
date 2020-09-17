package com.d9nich.antAlgorithm;

public class Ant {
    private final double[][] PHEROMONE;
    private final int[][] DISTANCE_MATRIX;
    private final int startPoint;

    public Ant(double[][] PHEROMONE, int[][] DISTANCE_MATRIX, int startPoint) {
        this.PHEROMONE = PHEROMONE;
        this.DISTANCE_MATRIX = DISTANCE_MATRIX;
        this.startPoint = startPoint;
    }
}
