package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        double[] prices = new double[] { 16.78, 9.76, 11.23 };
        int[] units = new int[] { 11, 9, 6 };
        List<String> descriptions = new ArrayList<String>();
        descriptions.add("Pen");
        descriptions.add("Mug");
        descriptions.add("T-Shirt");

        DataOutputStream out = null;
        DataInputStream in = null;

        try {
            FileOutputStream fos = new FileOutputStream("invoice.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            out = new DataOutputStream(bos);
            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descriptions.get(i));
            }

            out.close();

            in = new DataInputStream(new BufferedInputStream(new FileInputStream("invoice.txt")));
            double total = 0.0, price;
            int unit = 0;
            String desc = null;
            try {
                while (true) {
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    total += price * unit;
                }
            } catch (EOFException e) {

                System.out.printf("Total: %f\n", total);
            } finally {
                in.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
