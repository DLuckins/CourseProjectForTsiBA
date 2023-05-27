package com.example.bapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer {
    private int operationID=0;
    private int idFrom;
    private String nameFrom;
    private String surnameFrom;
    private double amount;
    private int idTo;
    private String nameTo;
    private String surnameTo;
    private double moneyTo;
    public boolean notFound=false;

    public Transfer(int operationID, int idFrom, String nameFrom, String surnameFrom,
                       double amount, int idTo, String nameTo, String surnameTo) {
        this.operationID = operationID;
        this.idFrom = idFrom;
        this.nameFrom = nameFrom;
        this.surnameFrom = surnameFrom;
        this.amount = amount;
        this.idTo = idTo;
        this.nameTo = nameTo;
        this.surnameTo = surnameTo;
    }
    public Transfer(){

    }
    public Transfer(int idTo, String nameTo,double amount){
        this.idTo=idTo;
        this.nameTo=nameTo;
        this.idFrom=UserMainScreenController.user.getId();
        this.nameFrom=UserMainScreenController.user.getName();
        this.surnameFrom=UserMainScreenController.user.getSurname();
        this.amount=amount;
    }
    public void transfer() throws SQLException {
        PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                "SELECT count(1) FROM badb.bank_accounts WHERE id = \"" + idTo + "\" AND" +
                        " Name = \""+nameTo+"\"");
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next()){
            if(resultset.getInt(1)==1){
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "SELECT Surname,Money FROM badb.bank_accounts WHERE id = \"" + idTo + "\" AND" +
                                " Name = \""+nameTo+"\"");
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    this.surnameTo=rs.getString("Surname");
                    this.moneyTo=rs.getDouble("Money");
                }
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "UPDATE badb.bank_accounts SET Money = \"" +(this.moneyTo+amount)+ "\" WHERE id = \"" + this.idTo + "\"");
                preparedStatement.execute();
                UserMainScreenController.user.setMoney(UserMainScreenController.user.getMoney()-amount);
                UserMainScreenController.user.updateBalanceDB();
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "INSERT INTO badb.operations (idFrom,nameFrom,surnameFrom,amount,idTo,nameTo,surnameTo) " +
                                "VALUES (\"" + idFrom + "\",\"" + nameFrom + "\",\"" + surnameFrom + "\",\"" + amount + "\"," +
                                "\"" + idTo + "\",\"" + nameTo + "\",\"" + surnameTo + "\")");
                preparedStatement.execute();

            }
            else{
                notFound=true;
            }
        }
    }
    public void revert(int opID) throws SQLException{
        PreparedStatement preparedStatement = BankingApplication.connection.prepareStatement(
                "SELECT count(1) FROM badb.operations WHERE operationID = \"" + opID + "\"");
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next()) {
            if (resultset.getInt(1) == 1) {
                double amount=0;
                int idTo=0;
                int idFrom=0;
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "SELECT idFrom,idTo,amount FROM badb.operations WHERE operationID = \"" + opID + "\"");
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    idTo = rs.getInt("idTo");
                    idFrom = rs.getInt("idFrom");
                    amount = rs.getDouble("amount");
                }
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "UPDATE badb.bank_accounts SET Money = (Money+" + amount + ") WHERE id = \""+ idFrom + "\"");
                preparedStatement.execute();
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "UPDATE badb.bank_accounts SET Money = (Money-" + amount + ") WHERE id = \""+ idTo + "\"");
                preparedStatement.execute();
                preparedStatement = BankingApplication.connection.prepareStatement(
                        "DELETE FROM badb.operations WHERE operationID = \""+ opID + "\"");
                preparedStatement.execute();

            }
        }
    }

    public Double getMoneyTo() {
        return moneyTo;
    }

    public void setMoneyTo(Double moneyTo) {
        this.moneyTo = moneyTo;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getSurnameFrom() {
        return surnameFrom;
    }

    public void setSurnameFrom(String surnameFrom) {
        this.surnameFrom = surnameFrom;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public String getSurnameTo() {
        return surnameTo;
    }

    public void setSurnameTo(String surnameTo) {
        this.surnameTo = surnameTo;
    }

    public int getOperationID() {
        return operationID;
    }

    public void setOperationID(int operationID) {
        this.operationID = operationID;
    }
}
