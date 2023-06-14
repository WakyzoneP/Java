package com.example;

public class Student {

    private String name;
    private int[] marks;
    private float avgM;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
        this.avgM = avgMarks();
    }

    public String getName() {
        return name;
    }

    public float avgMarks() {
        this.avgM = this.calculateAvgMark();
        return this.avgM;
    }

    private float calculateAvgMark()
    {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return (float) sum / marks.length;
    }
    
}
