package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        //create the dealership with no inventory
        this.inventory = new ArrayList<>();
    }

    //add vehicles to the inventory
    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    //get all the vehicles
    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public void removeVehicle(Vehicle vehicle) {
        //take the vehicle passed in out of the inventory list
        this.inventory.remove(vehicle);
    }

    public ArrayList<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            BigDecimal vehiclePrice = vehicle.getPrice();
            if (vehiclePrice.compareTo(min) > 0 || (vehiclePrice.compareTo(max) < 0)) {
                continue;
            }
            matches.add(vehicle);
        }

        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                matches.add(vehicle);
            }
        }

        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            int vehicleYear = vehicle.getYear();
            if (vehicleYear <= min && vehicleYear >= max) {
                matches.add(vehicle);
            }
        }

        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matches.add(vehicle);
            }
        }

        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            int vehicleMileage = vehicle.getYear();
            if (vehicleMileage <= min && vehicleMileage >= max) {
                matches.add(vehicle);
            }
        }

        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> matches = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            String vehicleType = vehicle.getVehicleType();
            if (vehicleType.equalsIgnoreCase(type)) {
                matches.add(vehicle);
            }
        }

        return matches;
    }

}