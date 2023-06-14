package com.example;

import java.util.ArrayList;
import java.util.List;

public class AppOOP {
    public static void main(String[] args) {
        double[] prices = new double[] { 16.78, 9.76, 11.23 };
        int[] units = new int[] { 11, 9, 6 };
        List<String> descriptions = new ArrayList<String>();
        descriptions.add("Pen");
        descriptions.add("Mug");
        descriptions.add("T-Shirt");

        Invoice invoice = new Invoice(prices, units, descriptions);
        invoice.saveInvoice("invoice2.txt");
        invoice.readAndCalcInvoice("invoice2.txt");
        System.out.printf("Total: %f\n", invoice.getTotal());
    }
}
