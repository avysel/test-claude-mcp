package com.example.banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.banking.Customer;
import com.example.banking.Account;

public class BankDatabase {
    private static Map<String, Customer> customers = new HashMap<>();
    private static Map<String, Account> accounts = new HashMap<>();
    
    public static void registerCustomer(Customer customer) {
        if (customer != null) {
            customers.put(customer.getId(), customer);
        }
    }
    
    public static void registerAccount(Account account) {
        if (account != null) {
            accounts.put(account.getId(), account);
        }
    }
    
    public static Customer findCustomerById(String customerId) {
        return customers.get(customerId);
    }
    
    public static List<Customer> findCustomersByName(String name) {
        List<Customer> result = new ArrayList<>();
        
        if (name == null || name.trim().isEmpty()) {
            return result;
        }
        
        String searchName = name.toLowerCase().trim();
        
        for (Customer customer : customers.values()) {
            String fullName = customer.getFullName().toLowerCase();
            if (fullName.contains(searchName)) {
                result.add(customer);
            }
        }
        
        return result;
    }
    
    public static Account findAccountById(String accountId) {
        return accounts.get(accountId);
    }
    
    public static List<Account> findAccountsByCustomer(Customer customer) {
        List<Account> result = new ArrayList<>();
        
        if (customer == null) {
            return result;
        }
        
        for (Account account : accounts.values()) {
            if (account.getOwner().getId().equals(customer.getId())) {
                result.add(account);
            }
        }
        
        return result;
    }
    
    public static void removeCustomer(String customerId) {
        if (customerId != null) {
            customers.remove(customerId);
        }
    }
    
    public static void removeAccount(String accountId) {
        if (accountId != null) {
            accounts.remove(accountId);
        }
    }
    
    public static int getCustomerCount() {
        return customers.size();
    }
    
    public static int getAccountCount() {
        return accounts.size();
    }
    
    public static void clearDatabase() {
        customers.clear();
        accounts.clear();
    }
}