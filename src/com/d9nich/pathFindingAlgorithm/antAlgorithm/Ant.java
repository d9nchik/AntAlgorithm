package com.d9nich.pathFindingAlgorithm.antAlgorithm;

import com.d9nich.pathFindingAlgorithm.Edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ant {
    public static final double ALFA = 2;//α
    public static final double BETTA = 3;//β
    public static double Lmin = Double.POSITIVE_INFINITY;

    private final double[][] PHEROMONE;
    private final double[][] MATRIX_OF_REVERSE_DISTANCE;
    private final int[][] DISTANCE_MATRIX;
    private final int startPoint;
    private double pathLength;

    private final ArrayList<Integer> path = new ArrayList<>();

    /**
     * Create object Ant
     *
     * @param PHEROMONE                  array, that represents pheromone on path
     * @param MATRIX_OF_REVERSE_DISTANCE ant seeing
     * @param DISTANCE_MATRIX            represents distance from one point to another
     * @param startPoint                 start point of path
     */
    public Ant(double[][] PHEROMONE, double[][] MATRIX_OF_REVERSE_DISTANCE, int[][] DISTANCE_MATRIX, int startPoint) {
        this.PHEROMONE = PHEROMONE;
        this.MATRIX_OF_REVERSE_DISTANCE = MATRIX_OF_REVERSE_DISTANCE;
        this.DISTANCE_MATRIX = DISTANCE_MATRIX;
        this.startPoint = startPoint;
    }

    /**
     * @return length of path
     */
    public double walkThrough() {
        path.clear();
        Set<Integer> pointsToVisit = new HashSet<>();
        for (int i = 0; i < MATRIX_OF_REVERSE_DISTANCE.length; i++) {
            pointsToVisit.add(i);
        }
        pointsToVisit.remove(startPoint);
        pathLength = walkThrough(startPoint, pointsToVisit);
        return pathLength;
    }

    /**
     * Helper method for walkThrough()
     *
     * @param currentPosition represents number of node, where ant stays.
     * @param pointsToVisit   represents nodes, that ant wants to visit
     * @return length of path
     */
    private double walkThrough(int currentPosition, Set<Integer> pointsToVisit) {
        if (pointsToVisit.isEmpty()) {
            return DISTANCE_MATRIX[currentPosition][startPoint];
        }

        ArrayList<Edge> edges = new ArrayList<>();
        pointsToVisit.forEach(e -> edges.add(new Edge(needToGo(currentPosition, e), e)));
        final double sum = edges.stream().mapToDouble(Edge::getDistance).sum();
        double randomValue = new Random().nextDouble();
        for (Edge edge : edges) {
            randomValue -= edge.getDistance() / sum;
            if (randomValue < 0) {
                final int newPosition = edge.getNumber();
                pointsToVisit.remove(newPosition);
                path.add(newPosition);
                return walkThrough(newPosition, pointsToVisit) + DISTANCE_MATRIX[currentPosition][newPosition];
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * ant influence on environment by spreading pheromone
     */
    public void spreadPheromone() {
        int current = startPoint;
        for (Integer position : path) {
            PHEROMONE[current][position] += Lmin / pathLength;
            current = position;
        }
    }

    /**
     * Calculating possibility, that ant will come these way
     *
     * @param from start node
     * @param to   end node
     * @return possibility
     */
    private double needToGo(int from, int to) {
        return Math.pow(PHEROMONE[from][to], ALFA) * Math.pow(MATRIX_OF_REVERSE_DISTANCE[from][to], BETTA);
    }

    /**
     * @return recent ant path
     */
    public int[] getPath() {
        int[] array = new int[path.size() + 1];
        array[0] = startPoint;
        for (int i = 0; i < path.size(); i++) {
            array[i + 1] = path.get(i);
        }
        return array;
    }
}
