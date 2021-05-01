package com.example.RESTAPI;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private Map<String, Client> customerMap = new HashMap<>();
    private static Database instance = new Database();
    public static Database getInstance(){
        return instance;
    }
    public String activeUser;

    private void DataStore(){
    }

    public Client getCustomer(String name){
        return customerMap.get(name);
    }

    public Client putCustomer(Client customer){
        customerMap.put(customer.getName(), customer);
        return customer;
    }

    public void createClient(String name, String passwd, double balance, Client.customerType type)
    {
        Client newClient = new Client(name,passwd,balance,type);
        customerMap.put(name,newClient);
    }

    public Client deleteCustomer(String name){
        return customerMap.remove(name);
    }
    public String printClients()
    {
        return customerMap.keySet().toString();
    }
    public boolean checkExisting(String name)
    {
        return customerMap.containsKey(name);
    }
}