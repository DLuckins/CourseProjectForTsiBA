package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMainScreenController implements Initializable {
    public static User user;
    @FXML
    private Text balance;

    @FXML
    private Text id;

    @FXML
    private Text welcomeText;

    @FXML
    private Button closeDeposit;

    @FXML
    private Button depositButton;

    @FXML
    private AnchorPane depositPanel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    balance.setText("Balance: " + user.getMoney());
    id.setText("ID: " + user.getId());
    welcomeText.setText("Welcome," + user.getName());
    }
    @FXML
    void enteredDepositButton(MouseEvent a){depositButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    void exitedDepositButton(MouseEvent a){depositButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}
    @FXML
    void depositButtonClick(ActionEvent a){
        depositPanel.setVisible(true);
    }
    @FXML
    void closeDepositClick(ActionEvent a){
        depositPanel.setVisible(false);
    }
}
