package com.example.banking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.banking.Account;
import com.example.banking.Address;
import com.example.banking.AccountType;

public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
    private Date createdDate;
    private List<Account> accounts;
    
    public Customer(String firstName, String lastName, String email, String phone, Address address) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdDate = new Date();
        this.accounts = new ArrayList<>();
    }
    
    public Account createAccount(double initialBalance, AccountType type) {
        Account account = new Account(this, initialBalance, type);
        accounts.add(account);
        return account;
    }
    
    public void addAccount(Account account) {
        if (account != null && !accounts.contains(account)) {
            accounts.add(account);
        }
    }
    
    public void removeAccount(Account account) {
        accounts.remove(account);
    }
    
    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }
    
    public double getTotalBalance() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
}