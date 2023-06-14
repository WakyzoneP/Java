package com.example;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

public class ObjectsSave {
    public static void main(String[] args) {
        ObjectsGraph objectsGraph = null;
        try {
            System.out.println("Serialize objects!");

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.txt"));

            URL url1 = new URL("http://www.google.com");
            URL url2 = url1;
            URL url3 = url1;
            objectsGraph = new ObjectsGraph(url1, url2);

            out.writeObject(objectsGraph);
            out.writeObject(url3);
            out.flush();

            boolean exp = ((objectsGraph.getUrl1() == url3) && (objectsGraph.getUrl2() == url3));
            System.out.println("Expresia este: " + exp);
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
