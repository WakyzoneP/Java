package com.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String... args) {
        int numberOfStudents = 2;
        Student[] grupa1049 = new Student[numberOfStudents];
        grupa1049[0] = new Student("Andreea", new int[]{7, 9, 5});
        grupa1049[1] = new Student("Alexandru", new int[]{6, 6, 10});

        for (int i = 0; i < grupa1049.length; i++) {
            System.out.println(grupa1049[i].getName() + " has an average mark of " + grupa1049[i].avgMarks());
        }
    }
}
