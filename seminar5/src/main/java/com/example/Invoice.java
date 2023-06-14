package com.example;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Invoice {
    private double[] prices;
    private int[] units;
    private List<String> descriptions;
    private double total;

    public Invoice(double[] prices, int[] units, List<String> descriptions) {
        this.prices = prices;
        this.units = units;
        this.descriptions = descriptions;
    }

    public void saveInvoice(String fileName) {
        DataOutputStream out = null;
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            out = new DataOutputStream(bos);
            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descriptions.get(i));
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAndCalcInvoice(String fileName) {
        DataInputStream in = null;
        try {
            in = new DataInputStream(new FileInputStream(fileName));
            double price;
            int unit = 0;
            String desc = null;
            try {
                this.total = 0.0;
                while (true) {
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    this.total += price * unit;
                }
            } catch (EOFException e) {
                // System.out.printf("Total: %f\n", total);
            } finally {
                in.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public double getTotal() {
        return total;
    }
}
