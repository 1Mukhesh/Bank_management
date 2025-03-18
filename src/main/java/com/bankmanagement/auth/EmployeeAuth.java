package com.bankmanagement.auth;

import com.bankmanagement.controller.CustomerController;
import com.bankmanagement.model.Employee;
import com.bankmanagement.dao.EmployeeDAO;
import java.util.Scanner;

public class EmployeeAuth {

    public static void employeeLogin(Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Employee employee = EmployeeDAO.validateEmployee(employeeId, password);

        if (employee != null) {
            System.out.println("Employee login successful!");
            employeeMenu(scanner);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void employeeMenu(Scanner scanner) {
        CustomerController customerController = new CustomerController();
        customerController.customerManagementMenu(scanner);
    }
}