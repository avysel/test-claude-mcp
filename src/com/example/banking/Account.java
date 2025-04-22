package com.example.banking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.banking.Customer;
import com.example.banking.Transaction;
import com.example.banking.AccountType;
import com.example.banking.TransactionType;
import com.example.banking.NotificationService;
import com.example.banking.InterestCalculator;

public class Account {
    private String id;
    private Customer owner;
    private double balance;
    private List<Transaction> transactions;
    private AccountType type;
    private Date createdDate;
    
    public Account(Customer owner, double initialBalance, AccountType type) {
        this.id = UUID.randomUUID().toString();
        this.owner = owner;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.type = type;
        this.createdDate = new Date();
        
        if (initialBalance > 0) {
            addTransaction(new Transaction(
                TransactionType.DEPOSIT,
                initialBalance,
                "Initial deposit",
                null,
                this
            ));
        }
    }
    
    public boolean withdraw(double amount, String description) {
        if (amount <= 0) {
            return false;
        }
        
        if (balance - amount < 0 && type != AccountType.CREDIT) {
            return false;
        }
        
        balance -= amount;
        
        Transaction transaction = new Transaction(
            TransactionType.WITHDRAWAL,
            amount,
            description,
            this,
            null
        );
        
        addTransaction(transaction);
        NotificationService.sendNotification(owner, "Withdrawal of $" + amount + " processed.");
        return true;
    }
    
    public boolean deposit(double amount, String description) {
        if (amount <= 0) {
            return false;
        }
        
        balance += amount;
        
        Transaction transaction = new Transaction(
            TransactionType.DEPOSIT,
            amount,
            description,
            null,
            this
        );
        
        addTransaction(transaction);
        NotificationService.sendNotification(owner, "Deposit of $" + amount + " received.");
        return true;
    }
    
    public boolean transfer(Account destination, double amount, String description) {
        if (destination == null || amount <= 0) {
            return false;
        }
        
        if (balance - amount < 0 && type != AccountType.CREDIT) {
            return false;
        }
        
        balance -= amount;
        destination.balance += amount;
        
        Transaction transaction = new Transaction(
            TransactionType.TRANSFER,
            amount,
            description,
            this,
            destination
        );
        
        addTransaction(transaction);
        destination.addTransaction(transaction);
        
        NotificationService.sendNotification(owner, "Transfer of $" + amount + " sent.");
        NotificationService.sendNotification(destination.getOwner(), "Transfer of $" + amount + " received.");
        
        return true;
    }
    
    private void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        InterestCalculator.processTransaction(this, transaction);
    }
    
    public String generateStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Account Statement for: ").append(id).append("\n");
        statement.append("Owner: ").append(owner.getFullName()).append("\n");
        statement.append("Current Balance: $").append(String.format("%.2f", balance)).append("\n");
        statement.append("Transaction History:\n");
        
        for (Transaction transaction : transactions) {
            statement.append("- ").append(transaction.toString()).append("\n");
        }
        
        return statement.toString();
    }
    
    public String getId() {
        return id;
    }
    
    public Customer getOwner() {
        return owner;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
    
    public AccountType getType() {
        return type;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
}