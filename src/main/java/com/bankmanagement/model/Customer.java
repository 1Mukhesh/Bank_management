package com.bankmanagement.model;

public class Customer {
    private String ssn;
    private String name;
    private String email;
    private String address;
    private String contactNumber;
    private String aadhaarNumber;
    private String panNumber;
    private String accountNumber;
    private String accountType;
    private String password;
    private double balance;

    // Constructors, Getters, and Setters...
    public Customer() {}
    public Customer(String ssn, String name, String email, String address, String contactNumber, String aadhaarNumber, String panNumber, String accountNumber, String accountType, String password, double balance) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.aadhaarNumber = aadhaarNumber;
        this.panNumber = panNumber;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.password = password;
        this.balance = balance;
    }

    // Getters and Setters and toString...
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ssn='" + ssn + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", aadhaarNumber='" + aadhaarNumber + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}