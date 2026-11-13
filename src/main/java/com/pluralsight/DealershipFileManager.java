package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class DealershipFileManager {
    private final String csvFile = "inventory.csv";

    // Sets up the Dealership.java class
    public Dealership getDealership() {
        //create the dealership variable
        Dealership dealership = null;
        try{
            //create a reader to read the csv file
            BufferedReader inventoryReader = new BufferedReader(new FileReader(csvFile));

            //read the first line (the dealership information
            String dealershipInfo = inventoryReader.readLine();

            //instantiate the dealership if the dealershipInfo was not null (was found in the file)
            if(dealershipInfo != null){

                String[] dealerShipParts = dealershipInfo.split("\\|");
                String name = dealerShipParts[0];
                String address = dealerShipParts[1];
                String phone = dealerShipParts[2];

                dealership = new Dealership(name, address, phone);
            }

            String vehicleLine;
            while((vehicleLine = inventoryReader.readLine()) != null){
                // Get all the info from the .csv line about the vehicle
                String[] vehicleParts = vehicleLine.split("\\|");
                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String type = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                BigDecimal price = new BigDecimal(vehicleParts[7]);

                //create the vehicle from the parts
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

                //add the vehicle to the dealership
                dealership.addVehicle(vehicle);
            }
        }catch(Exception e){
            System.out.println("Error reading the file: " + e.getMessage());
        }

        //after we do all the work to create the dealership we return it
        return dealership;
    }

    /*
    Rewrite the entire inventory.csv file with the current information from
    Dealership.inventory ArrayList<>
    */
    public void saveDealership(Dealership dealership){
        // Read the dealership information
        String dealershipInfo = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            dealershipInfo = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading dealership information: " + e.getMessage());
            return; // Stop so you don't overwrite the file if reading fails
        }

        // Rewrite all the vehicles inside the dealership inventory
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Write the first line (dealership information)
            writer.write(dealershipInfo);
            writer.newLine();

            // Write the vehicles in the inventory
            ArrayList<Vehicle> inventory = dealership.getAllVehicles();
            for (Vehicle vehicle : inventory) {
                String csvLine = vehicle.toString();

                writer.write(csvLine);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving inventory to CSV: " + e.getMessage());
        }
    }

}