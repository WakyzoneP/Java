package com.example;

public class App {
    public static void main(String[] args) {
        // 1. Pure Polymorphism
        // 2. Class-cast-exception
        // 3. Interface as Type

        // 1.
        Vehicle vehicle = null;
        Car car = new Car(1400, "5");
        Ship ship = new Ship(15000, 100, 321);
        vehicle = car;
        System.out.println(vehicle.display());
        // 2000 cod
        vehicle = ship;
        System.out.println(vehicle.display());

        // 2.
        Vehicle vehicle2 = new Vehicle(1000);
        vehicle2 = car;
        try {
            ship = (Ship) vehicle2;
            ship.setCapacity(150);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        //3.
        Movement movement1 = new Ship(2800, 8.5f, 123);
        Movement movement = new Car(2800, "5");
        System.out.println(movement.display());


        //------
        try (Car car2 = new Car(3500, "3");) {
            car2.display();
            System.out.println("try block finished");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("main prog finished");
    }
}
