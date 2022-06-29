package com.example.tiabokyfx.Database;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String dbName ="tiabokyfx";
        String dbUser = "root";
        String dbPassword = "";
        String url = "jdbc:mysql://localhost:3306/" + dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,dbUser,dbPassword);
        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }

        return databaseLink;
    }
}
