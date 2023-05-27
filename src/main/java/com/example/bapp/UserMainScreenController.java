package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Text loanText;

    @FXML
    private AnchorPane depositPanel;

    @FXML
    private TextField amountField;

    @FXML
    private Text depositError;

    @FXML
    private TextField loanAmount;

    @FXML
    private Text loanError;

    @FXML
    private TextField removeAmountField;

    @FXML
    private TextField payOffAmount;

    @FXML
    private Text withdrawError;
    @FXML
    private Text payOffError;

    @FXML
    private AnchorPane loanPanel;

    @FXML
    private AnchorPane payOffPanel;

    @FXML
    private AnchorPane withdrawalPanel;

    @FXML
    private AnchorPane transferPanel;

    @FXML
    private AnchorPane deletePanel;

    @FXML
    private BorderPane mainPanel;

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
    void closeClick(ActionEvent a){
        ((Node)a.getSource()).getParent().setVisible(false);
        if(mainPanel.isDisabled()){
            mainPanel.setDisable(false);
        }
    }
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
    void loanButton(ActionEvent event) {
        loanPanel.setVisible(true);
    }
    @FXML
    void payoffButton(ActionEvent event) {
        payOffPanel.setVisible(true);
        loanText.setText("Current loan: " + user.getLoanAmount());
    }
    @FXML
    void addButtonClick(ActionEvent a){
        if(!amountField.getText().isBlank()){
            try{
                double amount = Double.parseDouble(amountField.getText());
                if(amount<=0){
                        depositError.setText("Please enter positive number");
                        depositError.setVisible(true);
                }
                else {
                    user.setMoney(user.getMoney() + amount);
                    user.updateBalanceDB();
                    balance.setText("Balance: " + user.getMoney());
                    depositPanel.setVisible(false);
                }
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
    void takeLoanClick(ActionEvent event) {
        if(!loanAmount.getText().isBlank()){
            try{
                double amount = Double.parseDouble(loanAmount.getText());
                if(amount<=0){
                    loanError.setText("Please enter positive number");
                    loanError.setVisible(true);
                }
                else{
                user.takeLoanDB(amount);
                user.setLoanAmount(user.getLoanAmount()+(amount*1.2));
                user.setMoney(user.getMoney()+amount);
                user.updateBalanceDB();
                balance.setText("Balance: " + user.getMoney());
                loanPanel.setVisible(false);
                }
            }
            catch (NumberFormatException e){
                loanError.setText("You have to input number");
                loanError.setVisible(true);
            }
            catch (SQLException e){
                loanError.setText("Sorry, something went wrong");
                loanError.setVisible(true);
            }
        }
        else{
            loanError.setText("Field is empty, try again");
            loanError.setVisible(true);
        }
    }
    @FXML
    void payOffClick(ActionEvent event) {
        if(!payOffAmount.getText().isBlank()){
            try{
                double amount = Double.parseDouble(payOffAmount.getText());
                if(amount<=0){
                    payOffError.setText("Please enter positive number");
                    payOffError.setVisible(true);
                }
                else if(amount>user.getMoney()){
                    payOffError.setText("You don't have enough money");
                    payOffError.setVisible(true);
                }
                else if(amount> user.getLoanAmount()){
                    payOffError.setText("Your loan is smaller than input!");
                    payOffError.setVisible(true);
                }
                else {
                    user.payLoanDB(amount);
                    user.setLoanAmount(user.getLoanAmount()-amount);
                    user.setMoney(user.getMoney() - amount);
                    user.updateBalanceDB();
                    balance.setText("Balance: " + user.getMoney());
                    payOffPanel.setVisible(false);
                }
            }
            catch (NumberFormatException e){
                payOffError.setText("You have to input number");
                payOffError.setVisible(true);
            }
            catch (SQLException e){
                payOffError.setText("Sorry, something went wrong");
                payOffError.setVisible(true);
            }
        }
        else{
            payOffError.setText("Field is empty, try again");
            payOffError.setVisible(true);
        }
    }



    @FXML
    void Exit(ActionEvent event) {
        javafx.application.Platform.exit();
    }
    @FXML
    void deleteAccountButton(ActionEvent event) {
        mainPanel.setDisable(true);
        deletePanel.setVisible(true);
    }
    @FXML
    void removeButtonClick(ActionEvent event) {
        if(!removeAmountField.getText().isBlank()){
            try{
                double amount = Double.parseDouble(removeAmountField.getText());
                if(amount>user.getMoney()) {
                    withdrawError.setText("Not enough money!");
                    withdrawError.setVisible(true);
                }
                else if(amount<=0){
                    withdrawError.setText("Please enter positive number");
                    withdrawError.setVisible(true);
                }
                else{
                    user.setMoney(user.getMoney() - amount);
                    user.updateBalanceDB();
                    balance.setText("Balance: " + user.getMoney());
                    withdrawalPanel.setVisible(false);
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
                if(amount>user.getMoney()) {
                    transferError.setText("Please enter positive number");
                    transferError.setVisible(true);
                }
                else if(amount<=0){
                    transferError.setText("Not enough money!");
                    transferError.setVisible(true);
                }
                else{
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
    @FXML
    void deleteAccount(ActionEvent event) {
        try {
            Helper helper = new Helper();
            helper.newScene(event, "BA", "BA.fxml");
            user.deleteUser();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
