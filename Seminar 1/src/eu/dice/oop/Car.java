package eu.dice.oop;

public class Car {
    private int regId;
    private String name;

    public Car(int regId, String name) {
        this.regId = regId;
        this.name = name;
    }

    public int getRegId() {
        return regId;
    }

    public String getName() {
        return name;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }
}