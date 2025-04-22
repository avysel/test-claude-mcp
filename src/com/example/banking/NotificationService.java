package com.example.banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.banking.Customer;

public class NotificationService {
    private static Map<String, List<String>> notifications = new HashMap<>();
    
    public static void sendNotification(Customer customer, String message) {
        if (customer == null || message == null || message.trim().isEmpty()) {
            return;
        }
        
        String customerId = customer.getId();
        
        if (!notifications.containsKey(customerId)) {
            notifications.put(customerId, new ArrayList<>());
        }
        
        notifications.get(customerId).add(message);
        
        sendEmail(customer.getEmail(), "Banking Notification", message);
        sendSMS(customer.getPhone(), message);
    }
    
    private static void sendEmail(String email, String subject, String body) {
        if (email == null || subject == null || body == null) {
            return;
        }
        
        System.out.println("Email sent to " + email + ": " + subject + " - " + body);
    }
    
    private static void sendSMS(String phoneNumber, String message) {
        if (phoneNumber == null || message == null) {
            return;
        }
        
        System.out.println("SMS sent to " + phoneNumber + ": " + message);
    }
    
    public static List<String> getCustomerNotifications(Customer customer) {
        if (customer == null) {
            return new ArrayList<>();
        }
        
        String customerId = customer.getId();
        
        if (!notifications.containsKey(customerId)) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>(notifications.get(customerId));
    }
    
    public static void clearNotifications(Customer customer) {
        if (customer == null) {
            return;
        }
        
        notifications.remove(customer.getId());
    }
}