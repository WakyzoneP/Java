package com.example;

public class Ship extends Vehicle {
    private float capacity;
    private int registrationNumber;

    public Ship() {
        super();
        this.capacity = 0;
        this.registrationNumber = 0;
    }

    public Ship(float weight, float capacity, int registrationNumber) {
        super(weight);
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String display() {
        return new String("Ship - w = " + this.getWeight() + " - cap = " + this.capacity + " - regNo = " + this.registrationNumber);
    }
}
