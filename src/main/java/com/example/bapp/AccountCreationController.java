package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountCreationController implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Helper helper=new Helper();
        helper.textLimit(txtaddress,80);
        helper.textLimit(txtname,20);
        helper.textLimit(txtsurname,20);
        helper.textLimit(txtnumber,11);
        helper.textLimit(txtpassword,20);
        helper.textLimit(txtusername,20);
    }
    @FXML
    void enteredRegister(MouseEvent a){registerButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    void exitedRegister(MouseEvent a){registerButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}

    @FXML
    void register(ActionEvent event) throws IOException{
        if(!txtusername.getText().isBlank()&&!txtpassword.getText().isBlank()&&!txtname.getText().isBlank()&&
                !txtsurname.getText().isBlank()&&!txtnumber.getText().isBlank()&&!txtaddress.getText().isBlank()) {
        String uname = txtusername.getText();
        String passwd = txtpassword.getText();
        String name = txtname.getText();
        String surname = txtsurname.getText();
        try {
        long number = Long.parseLong(txtnumber.getText());
        String address = txtaddress.getText();

            PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                    "INSERT INTO badb.bank_accounts (Name,Surname,Username,Password,Address,Phone_number) " +
                            "VALUES (\"" + name + "\",\"" + surname + "\",\"" + uname + "\",\"" + passwd + "\"," +
                            "\"" + address + "\"," + number + ")");
            preparedStatement.execute();
            preparedStatement = BankingApplication.connection.prepareStatement(
                    "SELECT id FROM badb.bank_accounts WHERE username = \"" + uname + "\" AND" +
                            " password = \"" + passwd + "\"");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                UserMainScreenController.user = new User(rs.getInt("id"),
                        name,
                        surname,
                        uname,
                        0,
                        number,
                        address,
                        0);
            }
            Helper helper =new Helper();
            helper.newScene(event,"BankingApp","UserMainScreen.fxml");
            System.out.println("user added!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            errorText.setText("Please enter only numbers in phone number field");
            errorText.setVisible(true);
        }
    }
        else {
            errorText.setText("Please,enter needed information");
            errorText.setVisible(true);
        }
    }
    @FXML
    void enteredReturn(MouseEvent a){returnButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    void exitedReturn(MouseEvent a){returnButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}
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
