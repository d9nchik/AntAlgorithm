package com.d9nich.pathFindingAlgorithm.antAlgorithm;

import com.d9nich.pathFindingAlgorithm.GreedyAlgorithm;
import com.d9nich.pathFindingAlgorithm.PathFindable;

import java.util.Random;

public class AntAlgorithm implements PathFindable {

    public static final double RO = 0.4;//ρ
    public static final int ANTS_NUMBER = 35;

    private final int[][] DISTANCE_MATRIX;
    private final double[][] MATRIX_OF_REVERSED_DISTANCE;
    private final double[][] PHEROMONE;
    private int[] path;
    private double pathLength;

    public AntAlgorithm(int[][] DISTANCE_MATRIX) {
        this.DISTANCE_MATRIX = DISTANCE_MATRIX;
        MATRIX_OF_REVERSED_DISTANCE = createMatrixOfReversedDistance(DISTANCE_MATRIX);
        PHEROMONE = createMatrixOfPheromone(DISTANCE_MATRIX.length);
        PathFindable pathFindable = new GreedyAlgorithm(DISTANCE_MATRIX);
        pathLength = Ant.Lmin = pathFindable.getLength();
        path = pathFindable.getPath();
        findShortestDistance();
    }

    private static double[][] createMatrixOfPheromone(int length) {
        final double[][] PHEROMONE = new double[length][length];
        for (int i = 0; i < PHEROMONE.length; i++) {
            for (int j = 0; j < PHEROMONE.length; j++) {
                if (i != j)
                    PHEROMONE[i][j] = 0.1;
            }
        }
        return PHEROMONE;
    }

    private void findShortestDistance() {
        final Ant[] ants = makeColony();

        System.out.println("Greedy algorithm length: " + pathLength);
        for (int i = 0; i < 1_000; i++) {
            for (Ant ant : ants) {
                final double antPathLength = ant.walkThrough();
                if (antPathLength < pathLength) {
                    pathLength = antPathLength;
                    path = ant.getPath();
                }
            }
            updatePheromone(ants, PHEROMONE);
            Ant.Lmin = pathLength;
            //Printing path length
            if (i % 20 == 0) {
                System.out.println(i + ") " + pathLength);
            }
        }
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

    private Ant[] makeColony() {
        final Ant[] ants = new Ant[ANTS_NUMBER];
        Random random = new Random();
        //making popularity
        for (int i = 0; i < ants.length; i++) {
            ants[i] = new Ant(PHEROMONE, MATRIX_OF_REVERSED_DISTANCE, DISTANCE_MATRIX,
                    random.nextInt(DISTANCE_MATRIX.length));
        }
        return ants;
    }

    @Override
    public int[] getPath() {
        return path;
    }

    @Override
    public double getLength() {
        return pathLength;
    }
}
