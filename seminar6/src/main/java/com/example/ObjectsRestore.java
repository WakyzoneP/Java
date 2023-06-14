package com.example;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;

public class ObjectsRestore {
    public static void main(String[] args) {
        ObjectsGraph objectsGraph = null;
        try {
            System.out.println("Objects restore!");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("test.txt"));

            objectsGraph = (ObjectsGraph) in.readObject();
            System.out.println(objectsGraph.toString());

            URL url3 = (URL) in.readObject();
            System.out.println("url3: " + url3);

            boolean exp = ((objectsGraph.getUrl1() == url3) && (objectsGraph.getUrl2() == url3));
            System.out.println("Expresia este: " + exp);

            if (in != null)
                in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
