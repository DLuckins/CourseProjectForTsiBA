package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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
    @FXML
    void enteredLogin(MouseEvent a){
        loginButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");
    }
    @FXML
    void exitedLogin(MouseEvent a){
        loginButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");
    }
    @FXML
    void enteredCreate(MouseEvent a){
        createButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");
    }
    @FXML
    void exitedCreate(MouseEvent a){
        createButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");
    }
    public void loginOnClick(ActionEvent a) throws IOException {
        Helper helper =new Helper();
        helper.newScene(a,"Login","Login.fxml");
    }
    public void createOnClick(ActionEvent a) throws IOException {
        Helper helper =new Helper();
        helper.newScene(a,"CreateAccount","AccountCreation.fxml");

    }
}