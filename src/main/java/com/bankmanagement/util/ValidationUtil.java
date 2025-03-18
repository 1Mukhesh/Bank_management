package com.bankmanagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidContactNumber(String contactNumber) {
        String regex = "^[0-9]{10}$";
        return contactNumber.matches(regex);
    }

    public static boolean isValidAadhaar(String aadhaarNumber) {
        String regex = "^[2-9]{1}[0-9]{11}$";
        return aadhaarNumber.matches(regex);
    }

    public static boolean isValidPAN(String panNumber) {
        String regex = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
        return panNumber.matches(regex);
    }
}