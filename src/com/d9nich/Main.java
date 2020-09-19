package com.d9nich;

import com.d9nich.pathFindingAlgorithm.antAlgorithm.AntAlgorithm;

public class Main {

    public static void main(String[] args) {
        final int[][] matrixOfDistance = MatrixDistanceGenerator.generate(150);
//        Arrays.stream(matrixOfDistance).forEach(e -> System.out.println(Arrays.toString(e)));

        final int[] shortestDistance = new AntAlgorithm(matrixOfDistance).getPath();
        System.out.println("Path: ");
        for (int number : shortestDistance) {
            System.out.print(number + 1 + "->");
        }
    }
}
