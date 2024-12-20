package net.appdojo.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSQL {

    public static void main(String[] args) {
        // JDBC URL for SQL Server Express
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=dev;user=devuser;password=Test1234";

        // Optional: For Windows Authentication, use this connection string instead
        // String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=your_database_name;integratedSecurity=true;";

        // Connection object
        Connection connection = null;

        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            connection = DriverManager.getConnection(url);

            // If connection is successful
            System.out.println("Connected to the SQL Server database successfully!");

        } catch (SQLException e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver not found: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}

