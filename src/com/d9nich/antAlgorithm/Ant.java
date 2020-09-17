package com.d9nich.antAlgorithm;

import com.d9nich.Edge;

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

    public Ant(double[][] PHEROMONE, double[][] MATRIX_OF_REVERSE_DISTANCE, int[][] DISTANCE_MATRIX, int startPoint) {
        this.PHEROMONE = PHEROMONE;
        this.MATRIX_OF_REVERSE_DISTANCE = MATRIX_OF_REVERSE_DISTANCE;
        this.DISTANCE_MATRIX = DISTANCE_MATRIX;
        this.startPoint = startPoint;
    }

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

    private double walkThrough(int currentPosition, Set<Integer> pointsToVisit) {
        if (pointsToVisit.isEmpty()) {
            return DISTANCE_MATRIX[currentPosition][startPoint];
        }

        ArrayList<Edge> edges = new ArrayList<>();
        pointsToVisit.forEach(e -> edges.add(new Edge(needToGo(currentPosition, e), e)));
        double randomValue = new Random().nextDouble() * edges.stream().mapToDouble(Edge::getDistance).sum();
        for (Edge edge : edges) {
            randomValue -= edge.getDistance();
            if (randomValue < 0) {
                final int newPosition = edge.getNumber();
                pointsToVisit.remove(newPosition);
                path.add(newPosition);
                return walkThrough(newPosition, pointsToVisit) + DISTANCE_MATRIX[currentPosition][newPosition];
            }
        }

        return -1;
    }

    public void spreadPheromone() {
        int current = startPoint;
        for (Integer position : path) {
            PHEROMONE[current][position] += Lmin / pathLength;
            current = position;
        }
    }

    private double needToGo(int from, int to) {
        return Math.pow(PHEROMONE[from][to], ALFA) * Math.pow(MATRIX_OF_REVERSE_DISTANCE[from][to], BETTA);
    }
}
