package com.d9nich;

import java.util.*;

public class GreedyAlgorithm {
    public static final int INFINITY = Integer.MAX_VALUE / 2;

    private GreedyAlgorithm() {
    }

    /**
     * Search approximate distance of travelling salesman problem
     *
     * @param matrixOfDistance - matrix of distance
     * @return approximate distance(L)
     */
    public static double findL(int[][] matrixOfDistance) {
        Set<Integer> pointsToVisit = new HashSet<>();
        for (int i = 1; i < matrixOfDistance.length; i++) {
            pointsToVisit.add(i);
        }
        return findL(matrixOfDistance, 0, pointsToVisit, 0);
    }

    private static double findL(int[][] matrixOfDistance, int startPoint, Set<Integer> pointsToVisit, int endPoint) {
        if (pointsToVisit.isEmpty()) {
            int finalDistance = matrixOfDistance[startPoint][endPoint];
            if (finalDistance < INFINITY)
                return finalDistance;
            else
                return -1;
        }

        int[] distance = matrixOfDistance[startPoint];
        ArrayList<Edge> edges = new ArrayList<>();
        pointsToVisit.forEach(e -> edges.add(new Edge(distance[e], e)));
        Collections.sort(edges);

        for (Edge edge : edges) {
            final int nextStartPoint = edge.getNumber();
            pointsToVisit.remove(nextStartPoint);
            double distanceOfPath = findL(matrixOfDistance, nextStartPoint, pointsToVisit, endPoint);
            if (distanceOfPath >= 0) {
                return distanceOfPath + edge.getDistance();
            }
            pointsToVisit.add(nextStartPoint);
        }
        return -1;
    }
}
