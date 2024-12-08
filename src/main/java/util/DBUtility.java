package util;
/*
@author Bhimashankar Teli
 */

import javax.xml.transform.Result;
import java.sql.*;

public class DBUtility {

    private Connection connection;
    String url = "jdbc:mysql://localhost:3306/mydatabase";
    String username = "username";
    String password = "password";
    String query = "SELECT * FROM mytable";


    // Method to establish a database connection


    // Method to disconnect from the database
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /*
    to get connection - we can use it any class
     */
    public static Connection getConenction(){

         String userName = "bteli";
         String password = "admin";
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        Connection con= null;

        try{
            Class.forName("com.mysql.cj.jdbc.driver");
             con = DriverManager.getConnection(url,userName,password);
          //  Statement stmt = con.createStatement();
         //   ResultSet rs = stmt.executeQuery("select * from table");



        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    return con;
    }

}
