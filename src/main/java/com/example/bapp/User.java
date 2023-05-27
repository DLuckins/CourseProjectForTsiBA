package com.example.bapp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String surname;
    private String username;
    private double money;
    private long number;
    private String address;
    private double loanAmount;

    public User(int id, String name, String surname, String username, double money, long number, String address, double loan) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.money = money;
        this.number = number;
        this.address = address;
        this.loanAmount=loan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void updateBalanceDB() throws SQLException {
        PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                "UPDATE badb.bank_accounts SET Money = \"" +this.money+ "\" WHERE id = \"" + this.id + "\"");
        preparedStatement.execute();
    }
    public void takeLoanDB(double amount) throws SQLException{
        PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                "UPDATE badb.bank_accounts SET Loan = \"" +(this.loanAmount+(amount*1.2))+ "\" WHERE id = \"" + this.id + "\"");
        preparedStatement.execute();
    }
    public void payLoanDB(double amount) throws SQLException{
        PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                "UPDATE badb.bank_accounts SET Loan = \"" +(this.loanAmount-amount)+ "\" WHERE id = \"" + this.id + "\"");
        preparedStatement.execute();
    }
    public void deleteUser() throws SQLException{
        PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                "DELETE FROM badb.bank_accounts WHERE id = \"" + this.id + "\"");
        preparedStatement.execute();
    }

}
