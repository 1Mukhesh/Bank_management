package com.bankmanagement.main;

import com.bankmanagement.auth.*;
import com.bankmanagement.dao.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Initialize the database connection
            DBConnection.getConnection();

            // Register shutdown hook to close the database on exit
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down the database...");
                DBConnection.shutdownDatabase();
            }));

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nBank Management System");
                System.out.println("1. Admin Login");
                System.out.println("2. Employee Login");
                System.out.println("3. Customer Login");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        AdminAuth.adminLogin(scanner);
                        break;
                    case 2:
                        EmployeeAuth.employeeLogin(scanner);
                        break;
                    case 3:
                        CustomerAuth.customerLogin(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 0);

            scanner.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
