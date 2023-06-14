//a. Create public class AutonomousCar must inherit the Car class and it adds the softwareVersion - int field
//b. for this class fields it is mandatory to implement getters and setters
//c. override virtual implementation for the infoVehicle() method (from the Vehicle interface and Car implementation), 
//to return the softwareVersion to a String
//d. the setters should throw Exception if the constraints are not fulfilled:
// - softwareVersion greater than 0

public class AutonomousCar extends Car {
    private int softwareVersion;

    public int getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(int softwareVersion) throws Exception {
        if(softwareVersion < 0)
            throw new Exception("The software version must be greater than 0");
        this.softwareVersion = softwareVersion;
    }

    @Override
    public String infoVehicle() {
        return Integer.toString(softwareVersion);
    }
}
