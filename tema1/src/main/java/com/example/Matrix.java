package com.example;

public class Matrix {
    
    private int[][] matrix;
    private int rows;
    private int columns;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[rows][columns];
    }

    public int get(int row, int column) {
        return matrix[row][column];
    }

    public void set(int row, int column, int value) {
        matrix[row][column] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result += matrix[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}
