package com.bankmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String DB_URL = "jdbc:derby:derby-database/bankDB;create=true";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection(DB_URL);
        createTablesIfNotExist(connection);
        return connection;
    }

    private static void createTablesIfNotExist(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Check if the table exists before creating it
            if (!tableExists(connection, "EMPLOYEES")) {
                statement.executeUpdate("CREATE TABLE Employees (" +
                        "employeeId INT PRIMARY KEY, " +
                        "firstName VARCHAR(50), " +
                        "lastName VARCHAR(50), " +
                        "email VARCHAR(255) UNIQUE, " +
                        "contactNumber VARCHAR(10), " +
                        "designation VARCHAR(255), " +
                        "salary DOUBLE, " +
                        "password VARCHAR(255))");
            }
            
            if (!tableExists(connection, "CUSTOMERS")) {
                statement.executeUpdate("CREATE TABLE Customers (" +
                        "ssn VARCHAR(7) PRIMARY KEY, " +
                        "name VARCHAR(50), " +
                        "email VARCHAR(255) UNIQUE, " +
                        "address VARCHAR(100), " +
                        "contactNumber VARCHAR(10) UNIQUE, " +
                        "aadhaarNumber VARCHAR(12) UNIQUE, " +
                        "panNumber VARCHAR(10) UNIQUE, " +
                        "accountNumber VARCHAR(20) UNIQUE, " +
                        "accountType VARCHAR(50), " +
                        "password VARCHAR(255), " +
                        "balance DOUBLE)");
            }

            if (!tableExists(connection, "ADMINS")) {
                statement.executeUpdate("CREATE TABLE Admins (" +
                        "username VARCHAR(50) PRIMARY KEY, " +
                        "password VARCHAR(255))");
            }

            // Insert default admin if not exists
            statement.executeUpdate("INSERT INTO Admins (username, password) " +
                    "SELECT 'admin', 'password' FROM SYSIBM.SYSDUMMY1 " +
                    "WHERE NOT EXISTS (SELECT 1 FROM Admins WHERE username = 'admin')");

        }
    }

    // Function to check if a table exists in Derby
    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        try (var resultSet = connection.getMetaData().getTables(null, null, tableName.toUpperCase(), null)) {
            return resultSet.next();
        }
    }
    
    public static void shutdownDatabase() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("XJ015")) {
                System.out.println("Derby database shut down successfully.");
            } else {
                e.printStackTrace();
            }
        }
    }

}