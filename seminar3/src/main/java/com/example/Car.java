package com.example;

public class Car extends Vehicle implements AutoCloseable {

    private static int numberOfCars;
    private String numberOfDoors;

    public Car() {
        super();
        this.numberOfDoors = "0";
        numberOfCars++;
    }

    public Car(float weight, String numberOfDoors) {
        super(weight);
        this.numberOfDoors = numberOfDoors;
        numberOfCars++;
    }

    public String getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(String numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public static int getNumberOfCars() {
        return numberOfCars;
    }

    @Override
    public void close() throws Exception {
        
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Car carResult = null;
        carResult = (Car)super.clone();
        carResult.numberOfDoors = this.numberOfDoors;
        Car.numberOfCars++;
        return carResult;
    }

    @Override
    public String display() {
        return new String("Car - w = " + this.getWeight() + " - doorNo = " + this.numberOfDoors);
    }

    
}
