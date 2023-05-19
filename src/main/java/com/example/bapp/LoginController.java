package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Button returnButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Text errorText;
    @FXML
    void enteredLoginButton(MouseEvent a){loginButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    public void exitedLoginButton(MouseEvent a){loginButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}
    @FXML
    void loginButton(ActionEvent event){
        if(!txtusername.getText().isBlank()&&!txtpassword.getText().isBlank()) {
            String username = txtusername.getText();
            String password = txtpassword.getText();
            try {
                PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                        "SELECT count(1) FROM badb.bank_accounts WHERE username = \"" + username + "\" AND" +
                                " password = \""+password+"\"");
                ResultSet resultset = preparedStatement.executeQuery();
                while (resultset.next()){
                    if(resultset.getInt(1)==1){
                        errorText.setVisible(false);
                    }
                    else{
                        errorText.setText("Username or password is incorrect");
                        errorText.setVisible(true);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            errorText.setText("Some fields are missing");
            errorText.setVisible(true);
        }
    }
    @FXML
    void enteredReturn(MouseEvent a){returnButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    public void exitedReturn(MouseEvent a){returnButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}
    @FXML
    void returnButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankingApplication.class.getResource("BA.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
