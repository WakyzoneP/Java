import java.io.*;
import java.util.*;

public class Utils {
    private static List<ElectronicDevices> list = null;

    public static List<ElectronicDevices> createPhones(int n) {
        list = new ArrayList<ElectronicDevices>();
        for (int i = 0; i < n; i++) {
            list.add(new Phone());
        }
        return list;
    }

    public static List<ElectronicDevices> readPhones(String file) {
        list = new ArrayList<ElectronicDevices>();
        try {
            Scanner in = new Scanner(new File(file));
            while (in.hasNextLine()) {
                Phone phone = new Phone();
                phone.setWeight(Float.parseFloat(in.nextLine()));
                phone.setDiagonal(Double.parseDouble(in.nextLine()));
                phone.setProducer(in.nextLine());

                list.add(phone);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void writeBinaryPhones(String file, List<ElectronicDevices> listP) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (var element : listP) {
                Phone p = (Phone) element;
                out.writeObject(p);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ElectronicDevices> readBinaryPhones(String file) {
        list = new ArrayList<>();
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            while (true) {
                list.add((Phone) in.readObject());
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
