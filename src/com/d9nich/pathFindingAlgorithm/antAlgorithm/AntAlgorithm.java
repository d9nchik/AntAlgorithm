package com.d9nich.pathFindingAlgorithm.antAlgorithm;

import java.util.Random;

public class AntAlgorithm {

    public static final double RO = 0.4;//ρ
    public static final int ANTS_NUMBER = 35;

    public static int[] findShortestDistance(int[][] distanceMatrix) {
        final double[][] PHEROMONE = new double[distanceMatrix.length][distanceMatrix.length];
        for (int i = 0; i < PHEROMONE.length; i++) {
            for (int j = 0; j < PHEROMONE.length; j++) {
                if (i != j)
                    PHEROMONE[i][j] = 0.1;
            }
        }

        double[][] matrixOfReversedDistance = createMatrixOfReversedDistance(distanceMatrix);
        final Ant[] ants = new Ant[ANTS_NUMBER];
        Random random = new Random();
        //making popularity
        for (int i = 0; i < ants.length; i++) {
            ants[i] = new Ant(PHEROMONE, matrixOfReversedDistance, distanceMatrix, random.nextInt(distanceMatrix.length));
        }

        PathFindable pathFindable = new GreedyAlgorithm(distanceMatrix);
        double length = Ant.Lmin = pathFindable.getLength();
        int[] path = pathFindable.getPath();
        System.out.println("Greedy algorithm length: " + length);
        for (int i = 0; i < 1_000; i++) {
            for (Ant ant : ants) {
                final double antPathLength = ant.walkThrough();
                if (antPathLength < length) {
                    length = antPathLength;
                    path = ant.getPath();
                }
            }
            updatePheromone(ants, PHEROMONE);
            Ant.Lmin = length;
            //Printing path length
            if (i % 20 == 0) {
                System.out.println(i + ") " + length);
            }
        }

        return path;
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

    private static void updatePheromone(Ant[] ants, double[][] pheromone) {
        for (int i = 0; i < pheromone.length; i++) {
            for (int j = 0; j < pheromone.length; j++) {
                pheromone[i][j] *= 1 - RO;
            }
        }

        for (Ant ant : ants) {
            ant.spreadPheromone();
        }
    }
}
