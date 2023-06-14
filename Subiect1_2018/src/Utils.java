// Create public class Utils which contains private static list field with interface as a type: List<Vehicle>
// Insert the following methods:
// a. public static List<Vehicle> createCars(int n) throws Exception - for creating an ArrayList of n elements, which are containing n default 
// Car objects and it using the static field of the class (list)
// b.  public static List<Vehicle> readCars (String file) 
// - for reading and parsing text files with string lines for creating Car objects
// (e.g. please see for example carsList.txt file); first line is the weight in kg, second line is price in EUR and third line is the producer
// hint: use RandomAccessFile and read / parse line by line (first is parsing for float - weight, second line is double - price, 
// third is String - producer)
// c. public static void writeBinaryCars(String file, List<Vehicle> listP) - for writing binary the cars into the file
// hint: use FileOutputStream with FileOutputStream to serialized/save the Car objects from the ArrayList of the cars objects
//d. public static List<Vehicle> readBinaryCars(String file) - for reading binary the Car objects from the File and creating the ArrayList

import java.util.*;
import java.io.*;

public class Utils {
    private static List<Vehicle> list;

    public static List<Vehicle> createCars(int n) throws Exception {
        list = new ArrayList<Vehicle>();
        for (int i = 0; i < n; ++i) {
            list.add(new Car());
        }
        return list;
    }

    public static List<Vehicle> readCars(String file) {
        List<Vehicle> carList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNext()) {
                Car newCar = new Car();
                newCar.setWeight(Float.parseFloat(scanner.next()));
                newCar.setPrice(Double.parseDouble(scanner.next()));
                newCar.setProducer(scanner.next());
                carList.add(newCar);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carList;
    }

    public static void writeBinaryCars(String file, List<Vehicle> listP) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (var vehicle : listP) {
                Car c = (Car) vehicle;
                out.writeObject(c);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Vehicle> readBinaryCars(String file) {
        ObjectInputStream in = null;
        List<Vehicle> carList = new ArrayList<>();
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            while (true) {
                Car c = (Car)in.readObject();
                carList.add(c);
            }
        } catch (EOFException eof) {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (

        Exception e) {
            e.printStackTrace();
        }
        return carList;
    }
}
