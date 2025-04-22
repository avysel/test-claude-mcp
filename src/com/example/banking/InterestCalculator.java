package com.example.banking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.example.banking.Account;
import com.example.banking.AccountType;
import com.example.banking.Transaction;
import com.example.banking.BankDatabase;

public class InterestCalculator {
    private static Map<String, Double> interestRates = new HashMap<>();
    private static Map<String, Double> accumulatedInterest = new HashMap<>();
    private static Map<String, Date> lastInterestCalculation = new HashMap<>();
    
    static {
        interestRates.put(AccountType.CHECKING.name(), 0.01);
        interestRates.put(AccountType.SAVINGS.name(), 0.025);
        interestRates.put(AccountType.CREDIT.name(), 0.085);
        interestRates.put(AccountType.INVESTMENT.name(), 0.045);
    }
    
    public static void processTransaction(Account account, Transaction transaction) {
        if (account == null || transaction == null) {
            return;
        }
        
        String accountId = account.getId();
        
        if (!accumulatedInterest.containsKey(accountId)) {
            accumulatedInterest.put(accountId, 0.0);
            lastInterestCalculation.put(accountId, account.getCreatedDate());
        }
        
        updateInterest(account);
    }
    
    private static void updateInterest(Account account) {
        if (account == null) {
            return;
        }
        
        String accountId = account.getId();
        Date now = new Date();
        Date lastCalculation = lastInterestCalculation.get(accountId);
        
        long daysDifference = TimeUnit.DAYS.convert(
            now.getTime() - lastCalculation.getTime(), 
            TimeUnit.MILLISECONDS
        );
        
        if (daysDifference < 1) {
            return;
        }
        
        double balance = account.getBalance();
        double interestRate = interestRates.get(account.getType().name()) / 365.0;
        double dailyInterest = balance * interestRate;
        
        double currentAccumulatedInterest = accumulatedInterest.get(accountId) + (dailyInterest * daysDifference);
        
        accumulatedInterest.put(accountId, currentAccumulatedInterest);
        lastInterestCalculation.put(accountId, now);
        
        if (currentAccumulatedInterest >= 0.01) {
            double interestToApply = Math.floor(currentAccumulatedInterest * 100) / 100;
            account.deposit(interestToApply, "Interest payment");
            accumulatedInterest.put(accountId, currentAccumulatedInterest - interestToApply);
        }
    }
    
    public static void creditMonthlyInterest() {
        for (String accountId : accumulatedInterest.keySet()) {
            Account account = BankDatabase.findAccountById(accountId);
            if (account != null) {
                updateInterest(account);
                double accumulated = accumulatedInterest.get(accountId);
                if (accumulated >= 0.01) {
                    double interestToApply = Math.floor(accumulated * 100) / 100;
                    account.deposit(interestToApply, "Monthly interest payment");
                    accumulatedInterest.put(accountId, accumulated - interestToApply);
                }
            }
        }
    }
    
    public static double getAccumulatedInterest(Account account) {
        if (account == null) {
            return 0.0;
        }
        
        String accountId = account.getId();
        
        if (!accumulatedInterest.containsKey(accountId)) {
            return 0.0;
        }
        
        updateInterest(account);
        return accumulatedInterest.get(accountId);
    }
    
    public static void setInterestRate(AccountType accountType, double rate) {
        if (accountType != null && rate >= 0) {
            interestRates.put(accountType.name(), rate);
        }
    }
    
    public static double getInterestRate(AccountType accountType) {
        if (accountType == null) {
            return 0.0;
        }
        return interestRates.getOrDefault(accountType.name(), 0.0);
    }
}