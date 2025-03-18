package com.bankmanagement.dao;

import com.bankmanagement.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    // Implement CRUD operations for Customer table
    public static boolean createCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Customers (ssn, name, email, address, contactNumber, aadhaarNumber, panNumber, accountNumber, accountType, password, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, customer.getSsn());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getContactNumber());
            preparedStatement.setString(6, customer.getAadhaarNumber());
            preparedStatement.setString(7, customer.getPanNumber());
            preparedStatement.setString(8, customer.getAccountNumber());
            preparedStatement.setString(9, customer.getAccountType());
            preparedStatement.setString(10, customer.getPassword());
            preparedStatement.setDouble(11, customer.getBalance());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Customer getCustomerBySSN(String ssn) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Customers WHERE ssn = ?")) {
            preparedStatement.setString(1, ssn);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString("ssn"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("contactNumber"),
                        resultSet.getString("aadhaarNumber"),
                        resultSet.getString("panNumber"),
                        resultSet.getString("accountNumber"),
                        resultSet.getString("accountType"),
                        resultSet.getString("password"),
                        resultSet.getDouble("balance"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Customers SET name = ?, email = ?, address = ?, contactNumber = ?, aadhaarNumber = ?, panNumber = ?, accountType = ? WHERE ssn = ?")) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getContactNumber());
            preparedStatement.setString(5, customer.getAadhaarNumber());
            preparedStatement.setString(6, customer.getPanNumber());
            preparedStatement.setString(7, customer.getAccountType());
            preparedStatement.setString(8, customer.getSsn());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCustomer(String ssn) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Customers WHERE ssn = ?")) {
            preparedStatement.setString(1, ssn);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Customer validateCustomer(String ssn, String password) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Customers WHERE ssn = ? AND password = ?")) {
            preparedStatement.setString(1, ssn);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString("ssn"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("contactNumber"),
                        resultSet.getString("aadhaarNumber"),
                        resultSet.getString("panNumber"),
                        resultSet.getString("accountNumber"),
                        resultSet.getString("accountType"),
                        resultSet.getString("password"),
                        resultSet.getDouble("balance"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}