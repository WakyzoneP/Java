// class UnSyncThread extends Thread {

//     private static int a = 0;
//     private static int b = 0;

//     public UnSyncThread(String name) {
//         super(name);
//     }

//     public void myMethond() {
//         System.out.println("Thread " + this.getName() + "; a = " + a + ", b = " + b);
//         a++;
//         try{
//             sleep((int)Math.random() * 1000);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//         b++;
//     }

//     @Override
//     public void run() {
//         for(var i = 0; i < 3; ++i)
//             myMethond();
//         System.out.println("Thread " + this.getName() + " finished");
//     }

// }

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        List<Element> elements = new ArrayList<Element>();
        int linii = 0;
        int coloane = 0;

        File file = new File("date/matrice.txt");

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] values = str.split(",");
                int linie = Integer.parseInt(values[0]);
                int coloana = Integer.parseInt(values[1]);
                double element = Double.parseDouble(values[2]);

                if (linii < linie)
                    linii = linie;
                if (coloane < coloana)
                    coloane = coloana;

                Element elementObj = new Element(linie, coloana, element);
                elements.add(elementObj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        linii += 1;
        coloane += 1;

        System.out.println("Dimensiunea minima a matricei este " + linii + " linii si " + coloane + " coloane");

        // 3.
        if (!elements.isEmpty()) {
            for (var linie = 0; linie < linii; ++linie) {
                double sum = 0;
                for (var element : elements)
                    if (linie == element.getLinie())
                        sum += element.getElement();
                // System.out.printf("%d:%f\n", linie, sum/coloane);
                System.out.println(linie + ":" + sum);
            }
        }

        try {
            // DataOutputStream in = new DataOutputStream(
            // new BufferedOutputStream(new FileOutputStream("date/diagonala.txt")));
            BufferedWriter in = new BufferedWriter(new FileWriter("date/diagonalaText.txt"));
            int size = Integer.min(linii, coloane);
            for (int index = 0; index < size; ++index) {
                Boolean gasit = false;
                for (var element : elements) {
                    if (index == element.getLinie() && index == element.getColoana()) {
                        // in.writeDouble(element.getElement());
                        in.write(Double.toString(element.getElement()));
                    }
                }
                if (gasit == false)
                    in.write(Double.toString(0));
                in.write(",");
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
