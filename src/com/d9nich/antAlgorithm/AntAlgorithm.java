package com.d9nich.antAlgorithm;

import java.util.Random;

public class AntAlgorithm {
    public static final double ALFA = 2;//α
    public static final double BETTA = 3;//β
    public static final double RO = 0.4;//ρ
    public static final int ANTS_NUMBER = 35;

    public static int findShortestDistance(int[][] distanceMatrix) {
        final double[][] PHEROMONE = new double[distanceMatrix.length][distanceMatrix.length];
        final Ant[] ants = new Ant[ANTS_NUMBER];
        Random random = new Random();
        for (int i = 0; i < ants.length; i++) {
            ants[i] = new Ant(PHEROMONE, distanceMatrix, random.nextInt(distanceMatrix.length));
        }
        return 0;
    }
}
