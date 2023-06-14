package com.example;

public class Vehicle extends Object implements Movement, Cloneable {
    private float weight;

    public Vehicle() {
        this.weight = 0;
    }

    public Vehicle(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public void startEngine() {

        System.out.println("Vehicle::start");
    }

    @Override
    public void stopEngine() {

        System.out.println("Vehicle::stop");
    }

    @Override
    public String display() {
        
        // String result = new String("Vehicle - w = " + this.weight);
        // return result;
        return new String("Vehicle - w = " + this.weight);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Vehicle vehicleResult = null;
        vehicleResult = (Vehicle)super.clone();
        vehicleResult.weight = this.weight;
        return vehicleResult;
    }
    
}
