package com.bankmanagement.util;

import java.util.Random;

public class RandomGenerator {

    public static String generateSSN() {
        Random random = new Random();
        StringBuilder ssn = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            ssn.append(random.nextInt(10));
        }
        return ssn.toString();
    }

    public static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}