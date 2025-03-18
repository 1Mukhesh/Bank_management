package com.bankmanagement.controller;

import com.bankmanagement.dao.CustomerDAO;
import com.bankmanagement.model.*;
import com.bankmanagement.util.RandomGenerator;
import com.bankmanagement.util.ValidationUtil;
import java.util.Scanner;

public class CustomerController {

    public void customerManagementMenu(Scanner scanner) {
        // Implement Customer CRUD operations
        int choice;
        do {
            System.out.println("\nCustomer Management Menu");
            System.out.println("1. Register Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerCustomer(scanner);
                    break;
                case 2:
                    viewCustomer(scanner);
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 0:
                    System.out.println("Returning to previous Menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
    public void customerInformationMenu(Scanner scanner){
        //Implement Customer View operations
        viewCustomer(scanner);
    }

    private void registerCustomer(Scanner scanner) {
        String ssn = RandomGenerator.generateSSN();
        System.out.println("Generated SSN: " + ssn);
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();
        if (!ValidationUtil.isValidContactNumber(contactNumber)) {
            System.out.println("Invalid contact number format.");
            return;
        }
        System.out.print("Enter Aadhaar Number: ");
        String aadhaarNumber = scanner.nextLine();
        if (!ValidationUtil.isValidAadhaar(aadhaarNumber)) {
            System.out.println("Invalid Aadhaar number format.");
            return;
        }
        System.out.print("Enter PAN Number: ");
        String panNumber = scanner.nextLine();
        if (!ValidationUtil.isValidPAN(panNumber)) {
            System.out.println("Invalid PAN number format.");
            return;
        }
        String accountNumber = RandomGenerator.generateAccountNumber();
        System.out.println("Generated Account Number: "+accountNumber);
        System.out.print("Enter Type of Account (salary, saving, current): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Customer customer = new Customer(ssn, name, email, address, contactNumber, aadhaarNumber, panNumber, accountNumber, accountType, password, 0.0);
        if (CustomerDAO.createCustomer(customer)) {
            System.out.println("Customer registered successfully.");
        } else {
            System.out.println("Failed to register customer.");
        }
    }

    private void viewCustomer(Scanner scanner) {
        System.out.print("Enter Customer SSN: ");
        String ssn = scanner.nextLine();

        Customer customer = CustomerDAO.getCustomerBySSN(ssn);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found or deleted.");
        }
    }

    private void updateCustomer(Scanner scanner) {
        System.out.print("Enter Customer SSN to update: ");
        String ssn = scanner.nextLine();

        Customer customer = CustomerDAO.getCustomerBySSN(ssn);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter Customer Name (" + customer.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            customer.setName(name);
        }
        System.out.print("Enter Email (" + customer.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            customer.setEmail(email);
        }
        System.out.print("Enter Address (" + customer.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            customer.setAddress(address);
        }
        System.out.print("Enter Contact Number (" + customer.getContactNumber() + "): ");
        String contactNumber = scanner.nextLine();
        if (!contactNumber.isEmpty()) {
            customer.setContactNumber(contactNumber);
        }
        System.out.print("Enter Aadhaar Number (" + customer.getAadhaarNumber() + "): ");
        String aadhaarNumber = scanner.nextLine();
        if (!aadhaarNumber.isEmpty()) {
            customer.setAadhaarNumber(aadhaarNumber);
        }
        System.out.print("Enter PAN Number (" + customer.getPanNumber() + "): ");
        String panNumber = scanner.nextLine();
        if (!panNumber.isEmpty()) {
            customer.setPanNumber(panNumber);
        }
        System.out.print("Enter Account Type (" + customer.getAccountType() + "): ");
        String accountType = scanner.nextLine();
        if (!accountType.isEmpty()) {
            customer.setAccountType(accountType);
        }

        if (CustomerDAO.updateCustomer(customer)) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }

    private void deleteCustomer(Scanner scanner) {
        System.out.print("Enter Customer SSN to delete: ");
        String ssn = scanner.nextLine();

        if (CustomerDAO.deleteCustomer(ssn)) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Failed to delete customer or Customer not found.");
        }
    }
}