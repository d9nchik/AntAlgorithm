package com.d9nich;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final int[][] matrixOfDistance = MatrixDistanceGenerator.generate(1500);
//        Arrays.stream(matrixOfDistance).forEach(e -> System.out.println(Arrays.toString(e)));
        long start = System.currentTimeMillis();
        System.out.println(GreedyAlgorithm.findL(matrixOfDistance));
        start = System.currentTimeMillis() - start;
        System.out.println(start);
    }
}
