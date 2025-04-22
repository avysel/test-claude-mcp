package com.example.banking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.example.banking.Account;
import com.example.banking.TransactionType;

public class Transaction {
    private String id;
    private TransactionType type;
    private double amount;
    private String description;
    private Date date;
    private Account source;
    private Account destination;
    
    public Transaction(TransactionType type, double amount, String description, Account source, Account destination) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = new Date();
        this.source = source;
        this.destination = destination;
    }
    
    public String getId() {
        return id;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Account getSource() {
        return source;
    }
    
    public Account getDestination() {
        return destination;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        
        StringBuilder sb = new StringBuilder();
        sb.append(formattedDate).append(" | ");
        sb.append(type).append(" | ");
        sb.append("$").append(String.format("%.2f", amount)).append(" | ");
        sb.append(description);
        
        if (source != null) {
            sb.append(" | From: ").append(source.getId().substring(0, 8));
        }
        
        if (destination != null) {
            sb.append(" | To: ").append(destination.getId().substring(0, 8));
        }
        
        return sb.toString();
    }
}