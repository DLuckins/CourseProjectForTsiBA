package com.example.bapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminScreenController implements Initializable {
    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> address;

    @FXML
    private TableColumn<User, Double> loan;

    @FXML
    private TableColumn<User, Double> money;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, Long> phone;

    @FXML
    private TableColumn<User, String> surname;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableView<Transfer> transactions;

    @FXML
    private TableColumn<Transfer, Integer> opID;

    @FXML
    private TableColumn<Transfer, Integer> idFrom;

    @FXML
    private TableColumn<Transfer, Integer> idTo;

    @FXML
    private TableColumn<Transfer, String> nameFrom;

    @FXML
    private TableColumn<Transfer, String> nameTo;

    @FXML
    private TableColumn<Transfer, String> surnameFrom;

    @FXML
    private TableColumn<Transfer, String> surnameTo;

    @FXML
    private TableColumn<Transfer, Double> amount;

    @FXML
    private TextField transactionId;

    @FXML
    private AnchorPane usersPanel;

    @FXML
    private AnchorPane transactionPanel;

    @FXML
    private TextField userTextField;
    ObservableList<User> list;
    ObservableList<Transfer> transactionObservableList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        initializeTransactionTable();
        try {
            PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                "SELECT id,Name,Surname,Username,Address,Phone_number,Money,Loan FROM badb.bank_accounts");

            updateTable(preparedStatement);
            preparedStatement = BankingApplication.connection.prepareStatement(
                    "SELECT * FROM badb.operations");
            updateTransactionTable(preparedStatement);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void initializeTable(){
    name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
    surname.setCellValueFactory(new PropertyValueFactory<User,String>("surname"));
    address.setCellValueFactory(new PropertyValueFactory<User,String>("address"));
    username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
    id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
    phone.setCellValueFactory(new PropertyValueFactory<User,Long>("number"));
    money.setCellValueFactory(new PropertyValueFactory<User,Double>("money"));
    loan.setCellValueFactory(new PropertyValueFactory<User,Double>("loanAmount"));
    }
    public void initializeTransactionTable(){
        opID.setCellValueFactory(new PropertyValueFactory<Transfer,Integer>("operationID"));
        idFrom.setCellValueFactory(new PropertyValueFactory<Transfer,Integer>("idFrom"));
        idTo.setCellValueFactory(new PropertyValueFactory<Transfer,Integer>("idTo"));
        nameFrom.setCellValueFactory(new PropertyValueFactory<Transfer,String>("nameFrom"));
        nameTo.setCellValueFactory(new PropertyValueFactory<Transfer,String>("nameTo"));
        surnameFrom.setCellValueFactory(new PropertyValueFactory<Transfer,String>("surnameFrom"));
        surnameTo.setCellValueFactory(new PropertyValueFactory<Transfer,String>("surnameTo"));
        amount.setCellValueFactory(new PropertyValueFactory<Transfer,Double>("amount"));
    }
    @FXML
    void enteredButton(MouseEvent a){
        ((Button)a.getSource()).setStyle("-fx-background-color:#F8F1F1;-fx-cursor: hand;");
    }
    @FXML
    void exitedButton(MouseEvent a){((Button)a.getSource()).setStyle("-fx-background-color:#E8AA42;-fx-cursor: default;");}
    @FXML
    void Exit(ActionEvent event) {
        javafx.application.Platform.exit();
    }
    @FXML
    void showUsers(ActionEvent event) {
        usersPanel.setVisible(true);
    }
    @FXML
    void showTransactions(ActionEvent event) {
        transactionPanel.setVisible(true);
    }
    @FXML
    void searchButton(ActionEvent event) {
        try {
            PreparedStatement preparedStatement;
            if(userTextField.getText().isBlank()){
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "SELECT id,Name,Surname,Username,Address,Phone_number,Money,Loan FROM badb.bank_accounts");
            }
            else {
                int id = Integer.parseInt(userTextField.getText());
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "SELECT id,Name,Surname,Username,Address,Phone_number,Money,Loan FROM badb.bank_accounts " +
                                "WHERE id = \"" + id + "\"");
            }
            updateTable(preparedStatement);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    void deleteButton(ActionEvent event) {
        try {
            PreparedStatement preparedStatement;
            if(!userTextField.getText().isBlank()){
                int id = Integer.parseInt(userTextField.getText());
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "DELETE FROM badb.bank_accounts WHERE id = \"" + id + "\"");
                preparedStatement.execute();
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "SELECT id,Name,Surname,Username,Address,Phone_number,Money,Loan FROM badb.bank_accounts");
                updateTable(preparedStatement);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void updateTable(PreparedStatement preparedStatement) throws SQLException{
        ResultSet rs = preparedStatement.executeQuery();
        List<User> userList=new ArrayList<>();
        while (rs.next()) {
            User user=new User(rs.getInt("id"),
                    rs.getString("Name"),
                    rs.getString("Surname"),
                    rs.getString("Username"),
                    rs.getDouble("Money"),
                    rs.getLong("Phone_number"),
                    rs.getString("Address"),
                    rs.getDouble("Loan"));
            userList.add(user);
        }
        list=FXCollections.observableList(userList);
        userTable.setItems(list);
    }

    private void updateTransactionTable(PreparedStatement preparedStatement) throws SQLException{
        ResultSet rs = preparedStatement.executeQuery();
        List<Transfer> transactionList=new ArrayList<>();
        while (rs.next()) {
            Transfer transaction=new Transfer(rs.getInt("operationID"),
                    rs.getInt("idFrom"),
                    rs.getString("nameFrom"),
                    rs.getString("surnameFrom"),
                    rs.getDouble("amount"),
                    rs.getInt("idTo"),
                    rs.getString("nameTo"),
                    rs.getString("surnameTo"));
            transactionList.add(transaction);
        }
        transactionObservableList=FXCollections.observableList(transactionList);
        transactions.setItems(transactionObservableList);
    }
    @FXML
    void revertButton(ActionEvent event){
        if(!transactionId.getText().isBlank()) {
            try {
                int opId=Integer.parseInt(transactionId.getText());
                Transfer transfer=new Transfer();
                transfer.revert(opId);
                PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                        "SELECT * FROM badb.operations");
                updateTransactionTable(preparedStatement);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
    @FXML
    void usersReturn(ActionEvent event) {
        ((Node)event.getSource()).getParent().setVisible(false);
    }

}
