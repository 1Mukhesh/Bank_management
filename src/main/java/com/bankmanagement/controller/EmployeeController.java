package com.bankmanagement.controller;

import com.bankmanagement.dao.EmployeeDAO;
import com.bankmanagement.model.Employee;
import com.bankmanagement.util.ValidationUtil;
import java.util.Scanner;

public class EmployeeController {
    // Implement CRUD operations for employees
    public void employeeManagementMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nEmployee Management Menu");
            System.out.println("1. Create Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createEmployee(scanner);
                    break;
                case 2:
                    viewEmployee(scanner);
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 0:
                    System.out.println("Returning to Admin Menu...");
                    break;
                default:
                	System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void createEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();
        if (!ValidationUtil.isValidContactNumber(contactNumber)) {
            System.out.println("Invalid contact number format.");
            return;
        }
        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Employee employee = new Employee(employeeId, firstName, lastName, email, contactNumber, designation, salary, password);
        if (EmployeeDAO.createEmployee(employee)) {
            System.out.println("Employee created successfully.");
        } else {
            System.out.println("Failed to create employee.");
        }
    }

    private void viewEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        Employee employee = EmployeeDAO.getEmployeeById(employeeId);
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        Employee employee = EmployeeDAO.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter First Name (" + employee.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            employee.setFirstName(firstName);
        }
        // Similar prompts for other fields...
        System.out.print("Enter Last Name (" + employee.getLastName() + "): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            employee.setLastName(lastName);
        }
        System.out.print("Enter Email (" + employee.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            employee.setEmail(email);
        }
        System.out.print("Enter Contact Number (" + employee.getContactNumber() + "): ");
        String contactNumber = scanner.nextLine();
        if (!contactNumber.isEmpty()) {
            employee.setContactNumber(contactNumber);
        }
        System.out.print("Enter Designation (" + employee.getDesignation() + "): ");
        String designation = scanner.nextLine();
        if (!designation.isEmpty()) {
            employee.setDesignation(designation);
        }
        System.out.print("Enter Salary (" + employee.getSalary() + "): ");
        String salaryStr = scanner.nextLine();
        if (!salaryStr.isEmpty()) {
            employee.setSalary(Double.parseDouble(salaryStr));
        }

        if (EmployeeDAO.updateEmployee(employee)) {
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Failed to update employee.");
        }
    }

    private void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        if (EmployeeDAO.deleteEmployee(employeeId)) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Failed to delete employee.");
        }
    }
}