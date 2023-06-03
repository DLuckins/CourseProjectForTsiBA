package com.example.bapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Helper helper=new Helper();
        helper.textLimit(txtpassword,20);
        helper.textLimit(txtusername,20);
    }
    @FXML
    void enteredLoginButton(MouseEvent a){loginButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    void exitedLoginButton(MouseEvent a){loginButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}

    @FXML
    void loginButton(ActionEvent event) throws IOException{
        if(!txtusername.getText().isBlank()&&!txtpassword.getText().isBlank()) {
            String username = txtusername.getText();
            String password = txtpassword.getText();
            if (username.equals("admin")&& password.equals("admin")) {
                Helper helper=new Helper();
                helper.newScene(event,"Admin","AdminScreen.fxml");
            }

            else {
                try {
                    username = Encoding.encode(txtusername.getText());
                    password = Encoding.encode(txtpassword.getText());
                    PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                            "SELECT count(1) FROM badb.bank_accounts WHERE username = \"" + username + "\" AND" +
                                    " password = \"" + password + "\"");
                    ResultSet resultset = preparedStatement.executeQuery();
                    while (resultset.next()) {
                        if (resultset.getInt(1) == 1) {
                            errorText.setVisible(false);
                            preparedStatement = BankingApplication.connection.prepareStatement(
                                    "SELECT * FROM badb.bank_accounts WHERE username = \"" + username + "\" AND" +
                                            " password = \"" + password + "\"");
                            ResultSet rs = preparedStatement.executeQuery();
                            while (rs.next()) {
                                UserMainScreenController.user = new User(rs.getInt("id"),
                                        rs.getString("Name"),
                                        rs.getString("Surname"),
                                        rs.getString("Username"),
                                        rs.getDouble("Money"),
                                        rs.getLong("Phone_number"),
                                        rs.getString("Address"),
                                        rs.getDouble("Loan"));
                            }
                            enterUserScene(event);
                        } else {
                            errorText.setText("Username or password is incorrect");
                            errorText.setVisible(true);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
                errorText.setText("Some fields are missing");
                errorText.setVisible(true);
            }

    }
    void enterUserScene(ActionEvent event) throws IOException{
    Helper helper =new Helper();
    helper.newScene(event,"BankingApp","UserMainScreen.fxml");
    }
    @FXML
    void enteredReturn(MouseEvent a){returnButton.setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");}
    @FXML
    void exitedReturn(MouseEvent a){returnButton.setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}
    @FXML
    void returnButton(ActionEvent event) throws IOException {
        Helper helper =new Helper();
        helper.newScene(event,"BA","BA.fxml");
    }
}
