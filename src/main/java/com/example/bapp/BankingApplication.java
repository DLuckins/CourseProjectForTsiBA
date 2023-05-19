package com.example.bapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankingApplication extends Application {
    public static Connection connection=null;
    @Override
    public void start(Stage stage) throws IOException {
        connection=sqlConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(BankingApplication.class.getResource("BA.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("BA");
        stage.setScene(scene);
        stage.show();
    }
    public Connection sqlConnection(){
        String url="jdbc:mysql://localhost:3306/badb";
        String user="root";
        String password="1234AsDf";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,user,password);
            System.out.println("Connection is ok!!!");
            return connection;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}