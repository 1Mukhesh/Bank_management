package com.bankmanagement.dao;

import com.bankmanagement.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    // Implement CRUD operations for Employee table
    public static boolean createEmployee(Employee employee) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Employees (employeeId, firstName, lastName, email, contactNumber, designation, salary, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getContactNumber());
            preparedStatement.setString(6, employee.getDesignation());
            preparedStatement.setDouble(7, employee.getSalary());
            preparedStatement.setString(8, employee.getPassword());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Employee getEmployeeById(int employeeId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Employees WHERE employeeId = ?")) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("contactNumber"),
                        resultSet.getString("designation"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateEmployee(Employee employee) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Employees SET firstName = ?, lastName = ?, email = ?, contactNumber = ?, designation = ?, salary = ? WHERE employeeId = ?")) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getContactNumber());
            preparedStatement.setString(5, employee.getDesignation());
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setInt(7, employee.getEmployeeId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEmployee(int employeeId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Employees WHERE employeeId = ?")) {
            preparedStatement.setInt(1, employeeId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Employee validateEmployee(int employeeId, String password) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Employees WHERE employeeId = ? AND password = ?")) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("contactNumber"),
                        resultSet.getString("designation"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}