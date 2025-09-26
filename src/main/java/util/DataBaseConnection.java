package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

public class DataBaseConnection {

    private static Connection connection = null;

    static {
        String url = "jdbc:postgresql://localhost:5432/subsystem";
        String user = "postgres";
        String password = "moussi@25";

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,password);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
