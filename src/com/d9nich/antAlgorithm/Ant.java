package com.d9nich.antAlgorithm;

import com.d9nich.Edge;
import com.d9nich.GreedyAlgorithm;
import com.d9nich.MatrixDistanceGenerator;

import java.util.*;

public class Ant {
    public static final double ALFA = 2;//α
    public static final double BETTA = 3;//β
    private final double[][] PHEROMONE;
    private final double[][] MATRIX_OF_REVERSE_DISTANCE;
    private final int startPoint;

    public Ant(double[][] PHEROMONE, double[][] MATRIX_OF_REVERSE_DISTANCE, int startPoint) {
        this.PHEROMONE = PHEROMONE;
        this.MATRIX_OF_REVERSE_DISTANCE = MATRIX_OF_REVERSE_DISTANCE;
        this.startPoint = startPoint;
    }

    public void walkThrough() {
        Set<Integer> pointsToVisit = new HashSet<>();
        for (int i = 0; i < MATRIX_OF_REVERSE_DISTANCE.length; i++) {
            pointsToVisit.add(i);
        }
        pointsToVisit.remove(startPoint);
        walkThrough(0, pointsToVisit);
    }

    private void walkThrough(int currentPosition, Set<Integer> pointsToVisit) {
        if (pointsToVisit.isEmpty()) {
            return;
        }

        ArrayList<Edge> edges = new ArrayList<>();
        pointsToVisit.forEach(e -> edges.add(new Edge(needToGo(currentPosition, e), e)));
        double randomValue = new Random().nextInt((int) Math.ceil(edges.stream().mapToDouble(Edge::getDistance).sum()));
        for (Edge edge : edges) {
            randomValue -= edge.getDistance();
            if (randomValue < 0) {
                pointsToVisit.remove(edge.getNumber());
                walkThrough(edge.getNumber(), pointsToVisit);
            }
        }
    }

    private double needToGo(int from, int to) {
        return Math.pow(PHEROMONE[from][to], ALFA) * Math.pow(MATRIX_OF_REVERSE_DISTANCE[from][to], BETTA);
    }
}
