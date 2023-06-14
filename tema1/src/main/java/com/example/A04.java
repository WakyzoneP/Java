package com.example;

public class A04 {

    public static void main(String[] args) {

        Matrix matrix = new Matrix(3, 3);

        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);

        matrix.set(1, 0, 4);
        matrix.set(1, 1, 5);
        matrix.set(1, 2, 6);

        matrix.set(2, 0, 7);
        matrix.set(2, 1, 8);
        matrix.set(2, 2, 9);

        System.out.println(matrix.toString());


    }
}
