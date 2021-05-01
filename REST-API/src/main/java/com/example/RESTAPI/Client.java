package com.example.RESTAPI;

public class Client {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static enum customerType {SINGLE, JOINT}

    private String name;
    private double balance;
    private customerType type;
    private String password;
    private boolean isLocked;

    Client(String name, String password, double balance,customerType type){
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.type = type;
    }
    public void lock()
    {
        isLocked= true;
    }
    public void unlock()
    {
        isLocked= false;
    }
    public boolean getLock()
    {
        return isLocked;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addToBalance(double value) { balance += value; }

    public void removeFromBalance(double value){
       balance -= value;
    }

    public customerType getType() {
        return type;
    }

    public void setType(customerType type) {
        this.type = type;
    }




}