package org.cda.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil  {

    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String database = "cruddbswing";
            String url = "jdbc:mysql://localhost:3306/";
            Connection con = DriverManager.getConnection(url + database,"root","admin");
            return con;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }

    }

}
