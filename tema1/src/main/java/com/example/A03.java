package com.example;

import java.util.Random;

public class A03 {

    public static void main(String[] args) {
        
        Random random = new Random();

        int numbers[] = new int[100];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        System.out.println("The random numbers are: " + java.util.Arrays.toString(numbers));

        int average = 0;
        for (int i = 0; i < numbers.length; i++) {
            average += numbers[i];
        }
        average /= numbers.length;

        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > average) {
                count++;
            }
        }

        System.out.println("Number of elements above the average is " + count);

    }
}
