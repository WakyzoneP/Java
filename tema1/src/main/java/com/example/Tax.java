package com.example;

public class Tax {
    private int tax;
    private double lowerLimit;
    private double upperLimit;

    public Tax(int tax, double lowerLimit, double upperLimit) {
        this.tax = tax;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public static double computeTax(Tax[] taxTable, double income) {
        double tax = 0;
        for (int i = 0; i < taxTable.length; i++) {
            if (taxTable[i].getLowerLimit() <= income && income <= taxTable[i].getUpperLimit()) {
                tax = taxTable[i].getTax() / 100.0 * income;
                break;
            }
        }
        return tax;
    }
}
