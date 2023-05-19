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
import java.sql.SQLException;

public class AccountCreationController {
    @FXML
    private Button returnButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtnumber;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtsurname;

    @FXML
    private TextField txtusername;
    @FXML
    private Text errorText;
    @FXML
    void enteredRegister(MouseEvent a){registerButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    public void exitedRegister(MouseEvent a){registerButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}

    @FXML
    void register(ActionEvent event){
        if(!txtusername.getText().isBlank()&&!txtpassword.getText().isBlank()&&!txtname.getText().isBlank()&&
                !txtsurname.getText().isBlank()&&!txtnumber.getText().isBlank()&&!txtaddress.getText().isBlank()) {
        String uname = txtusername.getText();
        String passwd = txtpassword.getText();
        String name = txtname.getText();
        String surname = txtsurname.getText();
        long number = Long.parseLong(txtnumber.getText());
        String address = txtaddress.getText();
        try {
            PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                    "INSERT INTO badb.bank_accounts (Name,Surname,Username,Password,Address,Phone_number) " +
                            "VALUES (\"" + name + "\",\"" + surname + "\",\"" + uname + "\",\"" + passwd + "\"," +
                            "\"" + address + "\"," + number + ")");
            preparedStatement.execute();
            System.out.println("user added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        else {
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
