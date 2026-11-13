package com.pluralsight;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start the app
        UserInterface ui = new UserInterface();
        ui.display(scanner);

        scanner.close();
    }
}