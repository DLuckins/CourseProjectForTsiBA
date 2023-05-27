package com.example.bapp;

public class Transaction {
    private int operationID;
    private int idFrom;
    private String nameFrom;
    private String surnameFrom;
    private double amount;
    private int idTo;
    private String nameTo;
    private String surnameTo;

    public Transaction(int operationID, int idFrom, String nameFrom, String surnameFrom,
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

    public int getOperationID() {
        return operationID;
    }

    public void setOperationID(int operationID) {
        this.operationID = operationID;
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
}
