package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    Button loginButton;
    @FXML
    Button createButton;
    public void enteredLogin(MouseEvent a){
        loginButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");
    }
    public void exitedLogin(MouseEvent a){
        loginButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");
    }
    public void enteredCreate(MouseEvent a){
        createButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");
    }
    public void exitedCreate(MouseEvent a){
        createButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");
    }
    public void loginOnClick(ActionEvent a) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankingApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage stage = (Stage)((Node)a.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public void createOnClick(ActionEvent a) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankingApplication.class.getResource("AccountCreation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage stage = (Stage)((Node)a.getSource()).getScene().getWindow();
        stage.setTitle("CreateAccount");
        stage.setScene(scene);
        stage.show();
    }
}