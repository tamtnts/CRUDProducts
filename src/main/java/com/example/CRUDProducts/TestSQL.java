package com.example.CRUDProducts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestSQL {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://SUPER:1433;databaseName=CRUDProducts;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "12345";

        Connection connection = null;

        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Attempt to establish a connection
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connection to SQL Server established successfully!");
            } else {
                System.out.println("Failed to establish connection to SQL Server.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the connection if it was established
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
