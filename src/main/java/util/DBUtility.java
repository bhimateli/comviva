package util;
/*
@author Bhimashankar Teli
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {

    private Connection connection;
    String url = "jdbc:mysql://localhost:3306/mydatabase";
    String username = "username";
    String password = "password";
    String query = "SELECT * FROM mytable";


    // Method to establish a database connection
    public void connect() throws SQLException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
    }

    // Method to disconnect from the database
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
