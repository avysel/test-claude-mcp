package com.example.banking;

import com.example.banking.Address;
import com.example.banking.Customer;
import com.example.banking.Account;
import com.example.banking.AccountType;
import com.example.banking.NotificationService;
import com.example.banking.InterestCalculator;
import com.example.banking.BankDatabase;

public class BankingApp {
    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Anytown", "CA", "90210", "USA");
        Customer customer = new Customer("John", "Doe", "john.doe@example.com", "555-123-4567", address);
        
        BankDatabase.registerCustomer(customer);
        
        Account checkingAccount = customer.createAccount(1000.0, AccountType.CHECKING);
        Account savingsAccount = customer.createAccount(5000.0, AccountType.SAVINGS);
        
        BankDatabase.registerAccount(checkingAccount);
        BankDatabase.registerAccount(savingsAccount);
        
        System.out.println("Initial accounts:");
        System.out.println("Checking balance: $" + checkingAccount.getBalance());
        System.out.println("Savings balance: $" + savingsAccount.getBalance());
        
        checkingAccount.withdraw(200.0, "ATM withdrawal");
        savingsAccount.deposit(500.0, "Salary deposit");
        checkingAccount.transfer(savingsAccount, 300.0, "Monthly savings");
        
        System.out.println("\nAfter transactions:");
        System.out.println("Checking balance: $" + checkingAccount.getBalance());
        System.out.println("Savings balance: $" + savingsAccount.getBalance());
        
        System.out.println("\nCustomer total balance: $" + customer.getTotalBalance());
        
        System.out.println("\nChecking account statement:");
        System.out.println(checkingAccount.generateStatement());
        
        System.out.println("\nSavings account statement:");
        System.out.println(savingsAccount.generateStatement());
        
        System.out.println("\nNotifications for customer:");
        for (String notification : NotificationService.getCustomerNotifications(customer)) {
            System.out.println("- " + notification);
        }
        
        InterestCalculator.creditMonthlyInterest();
        
        System.out.println("\nCurrent interest rates:");
        for (AccountType type : AccountType.values()) {
            System.out.println(type + ": " + (InterestCalculator.getInterestRate(type) * 100) + "%");
        }
        
        // Création d'un nouveau type de compte CUSTOM_TYPE
        System.out.println("\nTentative de création d'un compte avec type CUSTOM_TYPE...");
        Account customAccount = customer.createAccount(2000.0, AccountType.CUSTOM_TYPE);
        BankDatabase.registerAccount(customAccount);
        System.out.println("Compte CUSTOM_TYPE créé avec succès, solde: $" + customAccount.getBalance());
    }
}