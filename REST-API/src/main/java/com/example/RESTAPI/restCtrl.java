package com.example.RESTAPI;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

@RestController
public class restCtrl {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/clients")
    public String getClients(){
        return Database.getInstance().printClients();
    }

    @PostMapping("/balance")
    public String balance(@RequestParam(value = "name") String name,
                          @RequestParam(value = "passwd") String passwd){
        if(Database.getInstance().checkExisting(name) &&
                Database.getInstance().getCustomer(name).getPassword().equals(passwd))
            return "Balance: "+Database.getInstance().getCustomer(name).getBalance();
        else return "No customer with that name or wrong password entered.";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam(value = "amount") double amount,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "passwd") String passwd){
        Client cl = Database.getInstance().getCustomer(name);
        if(amount<0)
        {
            return "Wrong value entered. Cannot deposit negative amount.";
        } else if(cl.getPassword().equals(passwd)) {
            if(cl.getType()== Client.customerType.JOINT)
            {
                if(cl.getLock())
                    return "Other user has access to account currently.";
                else {
                    cl.lock();
                    cl.addToBalance(amount);
                    cl.unlock();
                    return "Deposited " + amount + " to joint account of " + cl.getName();
                }
            } else {
                cl.addToBalance(amount);
                return "Added " + amount + " to " + cl.getName() + "'s account";
            }
        } else return "Wrong password.";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam(value = "amount") double amount,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "passwd") String passwd){
        Client cl = Database.getInstance().getCustomer(name);
        if(amount<0)
            return "Wrong value entered. Cannot withdraw negative amount.";
        else
            if(cl.getPassword().equals(passwd)) {
                if (cl.getBalance() < amount)
                    return "Insufficient funds.";
                else {
                    if(cl.getType()== Client.customerType.JOINT) {

                        if (cl.getLock())
                            return "Other user has access to account currently.";
                        else {
                            cl.lock();
                            cl.removeFromBalance(amount);
                            cl.unlock();
                            return "Withdrew " + amount + " from joint account of " + cl.getName();
                        }
                    } else cl.removeFromBalance(amount);
                    return "Withdrew " + amount + " from " + cl.getName() + "'s account";
                }
            } else return "Wrong password.";
    }

    @PostMapping("/joint")
    public String createJointAcc(@RequestParam(value = "nameFirst") String n1,
                                 @RequestParam(value = "nameSecond") String n2,
                                 @RequestParam(value = "balance") double balance,
                                 @RequestParam(value = "passwd") String passwd)
    {
        if(n1.equals(n2))
            return "Error: nameFirst != nameSecond";
        String jointName = n1+" "+n2;
        if(balance<0)
            return "Incorrect balance.";

        Database.getInstance().createClient(jointName,passwd,balance, Client.customerType.JOINT);
        return "New joint account created. Passwd: "+passwd;
    }
}
