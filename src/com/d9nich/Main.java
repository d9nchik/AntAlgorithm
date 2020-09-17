package com.d9nich;

import com.d9nich.antAlgorithm.AntAlgorithm;

public class Main {

    public static void main(String[] args) {
        final int[][] matrixOfDistance = MatrixDistanceGenerator.generate(150);
//        Arrays.stream(matrixOfDistance).forEach(e -> System.out.println(Arrays.toString(e)));
        System.out.println(AntAlgorithm.findShortestDistance(matrixOfDistance));
    }
}
