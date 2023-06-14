import java.util.ArrayList;
import java.util.List;

public class ProgMainPlayGround {
    public static void main(String[] args) {
        try {
            AutonomousCar ac1 = new AutonomousCar();
            ac1.setWeight(10);
            ac1.setPrice(5);
            ac1.setProducer("irina");
            ac1.setSoftwareVersion(2);
            AutonomousCar ac2 = new AutonomousCar();
            ac2.setWeight(20);
            ac2.setPrice(5);
            ac2.setProducer("irina");
            ac2.setSoftwareVersion(2);
            System.out.println(
                    ac1.getWeight() + " " + ac1.getPrice() + " " + ac1.getProducer() + " " + ac1.infoVehicle());
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            vehicles.add(ac1);
            vehicles.add(ac2);
            Utils.writeBinaryCars("test.bin", vehicles);
            vehicles = Utils.readBinaryCars("test.bin");

            for (var vehicle : vehicles) {
                Car c = (Car) vehicle;
                System.out.println(c.getWeight() + " " + c.getPrice() + " " + c.getProducer() + " "
                        + c.infoVehicle() + " " + c.infoVehicle());
            }

            VectorThread vt = new VectorThread("test.bin");
            vt.run();
            System.out.println(vt.getAvgWeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
