package com.d9nich;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Arrays.stream(MatrixDistanceGenerator.generate(15)).forEach(e -> System.out.println(Arrays.toString(e)));
    }
}
