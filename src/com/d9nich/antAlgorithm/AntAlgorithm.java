package com.d9nich.antAlgorithm;

import java.util.Random;

public class AntAlgorithm {
    public static final double ALFA = 2;//α
    public static final double BETTA = 3;//β
    public static final double RO = 0.4;//ρ
    public static final int ANTS_NUMBER = 35;

    public static int findShortestDistance(int[][] distanceMatrix) {
        final double[][] PHEROMONE = new double[distanceMatrix.length][distanceMatrix.length];
        for (int i = 0; i < PHEROMONE.length; i++) {
            for (int j = 0; j < PHEROMONE.length; j++) {
                if (i != j)
                    PHEROMONE[i][j] = 0.1;
            }
        }

        double[][] matrixOfReversedDistance = new double[distanceMatrix.length][distanceMatrix.length];
        final Ant[] ants = new Ant[ANTS_NUMBER];
        Random random = new Random();
        for (int i = 0; i < ants.length; i++) {
            ants[i] = new Ant(PHEROMONE, matrixOfReversedDistance, random.nextInt(distanceMatrix.length));
        }
        return 0;
    }

    private static double[][] createMatrixOfReversedDistance(int[][] distanceMatrix) {
        double[][] matrixOfReversedDistance = new double[distanceMatrix.length][distanceMatrix.length];
        for (int i = 0; i < matrixOfReversedDistance.length; i++) {
            for (int j = 0; j < matrixOfReversedDistance.length; j++) {
                if (i != j)
                    matrixOfReversedDistance[i][j] = 1.0 / distanceMatrix[i][j];
            }
        }

        return matrixOfReversedDistance;
    }
}
