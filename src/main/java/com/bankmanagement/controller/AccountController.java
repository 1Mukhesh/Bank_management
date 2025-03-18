package com.bankmanagement.controller;

import com.bankmanagement.dao.AccountDAO;
import com.bankmanagement.model.Customer;
import java.util.Scanner;

public class AccountController {

    public void accountMenu(Scanner scanner, Customer customer) {
        int choice;
        do {
            System.out.println("\nAccount Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    checkBalance(customer);
                    break;
                case 2:
                    withdrawMoney(scanner, customer);
                    break;
                case 3:
                    depositMoney(scanner, customer);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void checkBalance(Customer customer) {
        double balance = AccountDAO.getBalance(customer.getAccountNumber());
        System.out.println("Account Balance: " + balance);
    }

    private void withdrawMoney(Scanner scanner, Customer customer) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (AccountDAO.withdraw(customer.getAccountNumber(), amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance or failed to withdraw.");
        }
    }

    private void depositMoney(Scanner scanner, Customer customer) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (AccountDAO.deposit(customer.getAccountNumber(), amount)) {
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Failed to deposit.");
        }
    }
}