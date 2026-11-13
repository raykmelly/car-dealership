package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Dealership dealership;

    public UserInterface() {

    }

    public void display(Scanner scanner){
        init();

        boolean appRunning = true;
        while(appRunning){
            // Display the menu options
            System.out.println("\n[Car Dealership Menu]");
            System.out.println("1 | Find vehicles by price");
            System.out.println("2 | Find vehicles by make/model");
            System.out.println("3 | Find vehicles by year range");
            System.out.println("4 | Find vehicles by color");
            System.out.println("5 | Find all vehicles by mileage");
            System.out.println("6 | Find vehicles by type");
            System.out.println("7 | List all vehicles");
            System.out.println("8 | Add a vehicle");
            System.out.println("9 | Remove a vehicle");
            System.out.println("10 | Sell/Lease a Vehicle");
            System.out.println("99 | Quit");
            System.out.print("-> Enter option: ");

            int response = scanner.nextInt();

            switch (response) {
                case 1 -> processGetVehiclesByPrice(scanner);
                case 2 -> processGetVehiclesByMakeModel(scanner);
                case 3 -> processGetVehiclesByYearRange(scanner);
                case 4 -> processGetVehiclesByColor(scanner);
                case 5 -> processGetVehiclesByMileage(scanner);
                case 6 -> processGetVehiclesByType(scanner);
                case 7 -> processGetAllVehiclesRequest();
                case 9 -> processAddVehicle(scanner);
                //case 10 -> processSellOrLeaseVehicle(scanner);
                case 99 -> appRunning = false;
            }
        }
    }

    public void processAddVehicle(Scanner scanner) {
        System.out.println("\n[Add a vehicle]");

        // Variables needed to create a Vehicle object
        int vin;
        int year;
        String make;
        String model;
        String vehicleType;
        String color;
        int odometer;
        BigDecimal price;

        // Prompt for vin
        while (true) {
            System.out.print("-> Enter vin number: ");
            try {
                vin = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Prompt for year
        while (true) {
            System.out.print("-> Enter vehicle year: ");
            try {
                year = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        scanner.nextLine();

        // Prompt for make
        while (true) {
            System.out.print("-> Enter vehicle make: ");
            try {
                make = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Prompt for model
        while (true) {
            System.out.print("-> Enter vehicle model: ");
            try {
                model = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Prompt for type
        while (true) {
            System.out.print("-> Enter vehicle type: ");
            try {
                vehicleType = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Prompt for color
        while (true) {
            System.out.print("-> Enter vehicle color: ");
            try {
                color = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Prompt for odometer
        while (true) {
            System.out.print("-> Enter odometer number: ");
            try {
                odometer = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Prompt for price
        while (true) {
            System.out.print("-> Enter vehicle price: ");
            try {
                price = scanner.nextBigDecimal();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Create the vehicle and add it to the inventory
        Vehicle vehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
        dealership.addVehicle(vehicle);
    }

    // Prompt the user for the vehicle type, the display results
    public void processGetVehiclesByType(Scanner scanner) {
        System.out.println("\n[Search by vehicle type]");

        // Get input for the vehicle type
        String type = null;
        while (type == null) {
            System.out.print("-> Enter vehicle type: ");
            try {
                type = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);

        scanner.nextLine();
    }

    // Prompts user for min / max mileage, displays the result
    public void processGetVehiclesByMileage(Scanner scanner) {
        System.out.println("\n[Search by mileage]");

        // Get input for the min mileage
        int minMileage = 0;
        while (minMileage == 0) {
            System.out.print("-> Enter the minimum mileage: ");
            try {
                minMileage = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Get input for the max year
        int maxMileage = 0;
        while (maxMileage == 0) {
            System.out.print("-> Enter the maximum mileage: ");
            try {
                maxMileage = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage,maxMileage);
        displayVehicles(vehicles);

        scanner.nextLine();
    }

    // Prompts user for vehicle color, displays them
    public void processGetVehiclesByColor(Scanner scanner) {
        System.out.println("\n[Search by color]");

        // Get input for the make
        String color = null;
        while (color == null) {
            System.out.print("-> Enter vehicle color: ");
            try {
                color = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);

        scanner.nextLine();
    }

    // Prompt user for min / max year for vehicle, displays them
    public void processGetVehiclesByYearRange(Scanner scanner) {
        System.out.println("\n[Search by year range]");

        // Get input for the min year
        int minYear = 0;
        while (minYear == 0) {
            System.out.print("-> Enter the minimum year: ");
            try {
                minYear = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Get input for the max year
        int maxYear = 0;
        while (maxYear == 0) {
            System.out.print("-> Enter the maximum year: ");
            try {
                maxYear = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(minYear,maxYear);
        displayVehicles(vehicles);

        scanner.nextLine();
    }

    // Prompt user for make / model, displays found vehicles
    public void processGetVehiclesByMakeModel(Scanner scanner) {
        System.out.println("\n[Search by make / model]");

        // Get input for the make
        String make = null;
        while (make == null) {
            System.out.print("-> Enter your vehicle make: ");
            try {
                make = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Get input for the model
        String model = null;
        while (model == null) {
            System.out.print("-> Enter your vehicle model: ");
            try {
                model = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid word.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make,model);
        displayVehicles(vehicles);

        scanner.nextLine();
    }

    // Prompts user to get the (min/max) value of a vehicle they want to search
    public void processGetVehiclesByPrice(Scanner scanner) {
        System.out.println("\n[Search by price range]");

        // Get input for the min search price
        BigDecimal min = null;
        while (min == null) {
            System.out.print("-> Enter your minimum price: ");
            try {
                min = scanner.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        // Get input for the max search price
        BigDecimal max = null;
        while (max == null) {
            System.out.print("-> Enter your minimum price: ");
            try {
                max = scanner.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(min,max);
        displayVehicles(vehicles);

        scanner.nextLine();
    }

    // Initializing method
    private void init(){
        //create a dealership file manager
        DealershipFileManager dealership = new DealershipFileManager();
        this.dealership = dealership.getDealership();
    }

    // Helper method: displays list of vehicles as a string line
    private void displayVehicles(ArrayList<Vehicle> vehicles){
        for(Vehicle vehicle : vehicles){
            //make this better, tables and such
            System.out.println(vehicle.toString());
        }
    }

    // Lists all the vehicles currently inside the dealership
    public void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }



}