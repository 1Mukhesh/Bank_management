package com.bankmanagement.auth;

import com.bankmanagement.controller.EmployeeController;
import com.bankmanagement.controller.CustomerController;
import com.bankmanagement.model.Admin;
import com.bankmanagement.dao.AdminDAO;
import java.util.Scanner;

public class AdminAuth {

    public static void adminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        Admin admin = AdminDAO.validateAdmin(username, password);

        if (admin != null) {
            System.out.println("Admin login successful!");
            adminMenu(scanner);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void adminMenu(Scanner scanner) {
        int choice;
        EmployeeController employeeController = new EmployeeController();
        CustomerController customerController = new CustomerController();

        do {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Employee Management");
            System.out.println("2. Customer Information");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    employeeController.employeeManagementMenu(scanner);
                    break;
                case 2:
                    customerController.customerInformationMenu(scanner);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}