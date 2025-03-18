package com.bankmanagement.auth;

import com.bankmanagement.controller.AccountController;
import com.bankmanagement.model.Customer;
import com.bankmanagement.dao.CustomerDAO;
import java.util.Scanner;

public class CustomerAuth {

    public static void customerLogin(Scanner scanner) {
        System.out.print("Enter SSN: ");
        String ssn = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Customer customer = CustomerDAO.validateCustomer(ssn, password);

        if (customer != null) {
            System.out.println("Customer login successful!");
            customerMenu(scanner, customer);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void customerMenu(Scanner scanner, Customer customer) {
        AccountController accountController = new AccountController();
        accountController.accountMenu(scanner, customer);
    }
}