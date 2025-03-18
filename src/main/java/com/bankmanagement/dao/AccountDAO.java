package com.bankmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public static double getBalance(String accountNumber) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT balance FROM Customers WHERE accountNumber = ?")) {
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static boolean withdraw(String accountNumber, double amount) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Customers SET balance = balance - ? WHERE accountNumber = ? AND balance >= ?")) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountNumber);
            preparedStatement.setDouble(3, amount);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deposit(String accountNumber, double amount) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Customers SET balance = balance + ? WHERE accountNumber = ?")) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountNumber);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}