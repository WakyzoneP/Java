//a. Create the class VectorThread which implements Runnable and contains 2 fields:
// -CarList with interface as type List<Vehicle>
// -avgWeight is a real(double) number for storing the average weight of the cars list
//b. In the constructor read the file using readBinaryCars static method from Utils
//c. provide get methods for the both fields (argList and avgWeignt) of the class
//d. Within the override run method (with the signature in Runnable interface)
// -the developer should go through the carsList and calculate the average of the weights from the car list objects(Car class-explicit cast)

import java.util.List;

public class VectorThread implements Runnable {
    private List<Vehicle> CarList;
    private double avgWeight;

    public VectorThread(String fileName)
    {
        CarList = Utils.readBinaryCars(fileName);
    }

    public List<Vehicle> getCarList() {
        return CarList;
    }

    public double getAvgWeight() {
        return avgWeight;
    }

    @Override
    public void run() {
        avgWeight = CarList.stream().mapToDouble(e -> ((Car)e).getWeight()).average().orElse(0);
    }
}
