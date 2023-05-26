package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.InputMismatchException;
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
    private AnchorPane depositPanel;

    @FXML
    private TextField amountField;

    @FXML
    private Text depositError;

    @FXML
    private TextField removeAmountField;

    @FXML
    private Text withdrawError;

    @FXML
    private AnchorPane withdrawalPanel;
    @FXML
    private AnchorPane transferPanel;

    @FXML
    private TextField transferAmount;

    @FXML
    private Text transferError;

    @FXML
    private TextField transferID;

    @FXML
    private TextField transferName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    balance.setText("Balance: " + user.getMoney());
    id.setText("ID: " + user.getId());
    welcomeText.setText("Welcome," + user.getName());
    }
    @FXML
    void enteredButton(MouseEvent a){
        ((Button)a.getSource()).setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");
    }
    @FXML
    void exitedButton(MouseEvent a){((Button)a.getSource()).setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}
    @FXML
    void enteredCloseButton(MouseEvent a){((Button)a.getSource()).setStyle(((Button)a.getSource()).getStyle()+"-fx-text-fill: white;-fx-cursor: hand;");}
    @FXML
    void exitedCloseButton(MouseEvent a){((Button)a.getSource()).setStyle(((Button)a.getSource()).getStyle()+"-fx-text-fill: black;-fx-cursor: default;");}
    @FXML
    void depositButtonClick(ActionEvent a){
        depositPanel.setVisible(true);
    }
    @FXML
    void withdrawButtonClick(ActionEvent a){
        withdrawalPanel.setVisible(true);
    }
    @FXML
    void transferMoneyButtonClick(ActionEvent a){
        transferPanel.setVisible(true);
    }
    @FXML
    void closeTransferClick(ActionEvent event) {
        transferPanel.setVisible(false);
    }
    @FXML
    void addButtonClick(ActionEvent a){
        if(!amountField.getText().isBlank()){
            try{
                double amount = Double.parseDouble(amountField.getText());
                user.setMoney(user.getMoney()+amount);
                user.updateBalanceDB();
                balance.setText("Balance: " + user.getMoney());
                depositPanel.setVisible(false);
            }
            catch (NumberFormatException e){
                depositError.setText("You have to input number");
                depositError.setVisible(true);
            }
            catch (SQLException e){
                depositError.setText("Sorry, something went wrong");
                depositError.setVisible(true);
            }
        }
        else{
            depositError.setText("Field is empty, try again");
            depositError.setVisible(true);
        }
    }
    @FXML
    void closeDepositClick(ActionEvent a){
        depositPanel.setVisible(false);
    }
    @FXML
    void closeWithdrawClick(ActionEvent event) {
        withdrawalPanel.setVisible(false);
    }
    @FXML
    void removeButtonClick(ActionEvent event) {
        if(!removeAmountField.getText().isBlank()){
            try{
                double amount = Double.parseDouble(removeAmountField.getText());
                if(user.getMoney()-amount>=0) {
                    user.setMoney(user.getMoney() - amount);
                    user.updateBalanceDB();
                    balance.setText("Balance: " + user.getMoney());
                    withdrawalPanel.setVisible(false);
                }
                else{
                    withdrawError.setText("Not enough money!");
                    withdrawError.setVisible(true);
                }
            }
            catch (NumberFormatException e){
                withdrawError.setText("You have to input number");
                withdrawError.setVisible(true);
            }
            catch (SQLException e){
                withdrawError.setText("Sorry, something went wrong");
                withdrawError.setVisible(true);
            }
        }
        else{
            withdrawError.setText("Field is empty, try again");
            withdrawError.setVisible(true);
        }
    }
    @FXML
    void transferButtonClick(ActionEvent event) {
        if(!transferAmount.getText().isBlank() && !transferID.getText().isBlank() && !transferName.getText().isBlank()){
            try{
                double amount = Double.parseDouble(transferAmount.getText());
                int id = Integer.parseInt(transferID.getText());
                if(user.getMoney()-amount>=0) {
                    Transfer transfer=new Transfer(id,transferName.getText(),amount);
                    transfer.transfer();
                    if(transfer.notFound){
                       transferError.setText("User not found");
                       transferError.setVisible(true);
                    }
                    else{
                    balance.setText("Balance: " + user.getMoney());
                    transferPanel.setVisible(false);
                    }
                }
                else{
                    transferError.setText("Not enough money!");
                    transferError.setVisible(true);
                }
            }
            catch (NumberFormatException e){
                transferError.setText("Incorrect input");
                transferError.setVisible(true);
            }
            catch (SQLException e){
                transferError.setText("Sorry, something went wrong");
                transferError.setVisible(true);
                e.printStackTrace();
            }
        }
        else{
            transferError.setText("Some fields are missing");
            transferError.setVisible(true);
        }
    }
}
