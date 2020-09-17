package com.d9nich;

import java.util.*;

public class GreedyAlgorithm {
    private GreedyAlgorithm() {
    }

    /**
     * Search approximate distance of travelling salesman problem
     *
     * @param matrixOfDistance - matrix of distance
     * @return approximate distance(L)
     */
    public static int findL(int[][] matrixOfDistance) {
        Set<Integer> pointsToVisit = new HashSet<>();
        for (int i = 1; i < matrixOfDistance.length; i++) {
            pointsToVisit.add(i);
        }
        return findL(matrixOfDistance, 0, pointsToVisit, 0);
    }

    private static int findL(int[][] matrixOfDistance, int startPoint, Set<Integer> pointsToVisit, int endPoint) {
        if (pointsToVisit.isEmpty()) {
            return matrixOfDistance[startPoint][endPoint];
        }

        int[] distance = matrixOfDistance[startPoint];
        ArrayList<Edge> edges = new ArrayList<>();
        pointsToVisit.forEach(e -> edges.add(new Edge(distance[e], e)));
        Collections.sort(edges);

        for (Edge edge : edges) {
            final int nextStartPoint = edge.number;
            pointsToVisit.remove(nextStartPoint);
            int distanceOfPath = findL(matrixOfDistance, nextStartPoint, pointsToVisit, endPoint);
            if (distanceOfPath >= 0) {
                return distanceOfPath + edge.distance;
            }
            pointsToVisit.add(nextStartPoint);
        }
        return -1;
    }

    private static class Edge implements Comparable<Edge> {
        private final int distance;
        private final int number;

        public Edge(int distance, int number) {
            this.distance = distance;
            this.number = number;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(distance, o.distance);
        }

        @Override
        public String toString() {
            return "Edge{" + "distance=" + distance +
                    ", number=" + number +
                    '}';
        }
    }
}
