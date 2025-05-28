# Banking Application Class Diagram

```mermaid
classDiagram
    class Customer {
        -String id
        -String firstName
        -String lastName
        -String email
        -String phone
        -Address address
        -Date createdDate
        -List~Account~ accounts
        +Customer(firstName, lastName, email, phone, address)
        +Account createAccount(initialBalance, type)
        +void addAccount(account)
        +void removeAccount(account)
        +List~Account~ getAccounts()
        +double getTotalBalance()
        +String getFullName()
        +String getId()
        +String getFirstName()
        +void setFirstName(firstName)
        +String getLastName()
        +void setLastName(lastName)
        +String getEmail()
        +void setEmail(email)
        +String getPhone()
        +void setPhone(phone)
        +Address getAddress()
        +void setAddress(address)
        +Date getCreatedDate()
    }

    class Account {
        -String id
        -Customer owner
        -double balance
        -List~Transaction~ transactions
        -AccountType type
        -Date createdDate
        +Account(owner, initialBalance, type)
        +boolean withdraw(amount, description)
        +boolean deposit(amount, description)
        +boolean transfer(destination, amount, description)
        -void addTransaction(transaction)
        +String generateStatement()
        +String getId()
        +Customer getOwner()
        +double getBalance()
        +List~Transaction~ getTransactions()
        +AccountType getType()
        +Date getCreatedDate()
    }

    class Address {
        -String street
        -String city
        -String state
        -String zipCode
        -String country
        +Address(street, city, state, zipCode, country)
        +String getStreet()
        +void setStreet(street)
        +String getCity()
        +void setCity(city)
        +String getState()
        +void setState(state)
        +String getZipCode()
        +void setZipCode(zipCode)
        +String getCountry()
        +void setCountry(country)
        +String toString()
    }

    class Transaction {
        -String id
        -TransactionType type
        -double amount
        -String description
        -Date date
        -Account source
        -Account destination
        +Transaction(type, amount, description, source, destination)
        +String getId()
        +TransactionType getType()
        +double getAmount()
        +String getDescription()
        +Date getDate()
        +Account getSource()
        +Account getDestination()
        +String toString()
    }

    class BankDatabase {
        -Map~String, Customer~ customers$
        -Map~String, Account~ accounts$
        +void registerCustomer(customer)$
        +void registerAccount(account)$
        +Customer findCustomerById(customerId)$
        +List~Customer~ findCustomersByName(name)$
        +Account findAccountById(accountId)$
        +List~Account~ findAccountsByCustomer(customer)$
        +void removeCustomer(customerId)$
        +void removeAccount(accountId)$
        +int getCustomerCount()$
        +int getAccountCount()$
        +void clearDatabase()$
    }

    class NotificationService {
        -Map~String, List~String~~ notifications$
        +void sendNotification(customer, message)$
        -void sendEmail(email, subject, body)$
        -void sendSMS(phoneNumber, message)$
        +List~String~ getCustomerNotifications(customer)$
        +void clearNotifications(customer)$
    }

    class InterestCalculator {
        -Map~String, Double~ interestRates$
        -Map~String, Double~ accumulatedInterest$
        -Map~String, Date~ lastInterestCalculation$
        +void processTransaction(account, transaction)$
        -void updateInterest(account)$
        +void creditMonthlyInterest()$
        +double getAccumulatedInterest(account)$
        +void setInterestRate(accountType, rate)$
        +double getInterestRate(accountType)$
    }

    class BankingApp {
        +void main(args)$
    }

    class AccountType {
        <<enumeration>>
        CHECKING
        SAVINGS
        CREDIT
        INVESTMENT
    }

    class TransactionType {
        <<enumeration>>
        DEPOSIT
        WITHDRAWAL
        TRANSFER
        PAYMENT
        REFUND
        FEE
        INTEREST
    }

    %% Relationships
    Customer ||--o{ Account : owns
    Customer ||--|| Address : has
    Account ||--o{ Transaction : contains
    Account ||--|| AccountType : has
    Account ||--|| Customer : belongs to
    Transaction ||--|| TransactionType : has
    Transaction ||--o| Account : source
    Transaction ||--o| Account : destination
    
    BankDatabase ..> Customer : manages
    BankDatabase ..> Account : manages
    NotificationService ..> Customer : notifies
    InterestCalculator ..> Account : calculates
    InterestCalculator ..> Transaction : processes
    InterestCalculator ..> AccountType : uses
    BankingApp ..> Customer : creates
    BankingApp ..> Account : creates
    BankingApp ..> Address : creates
    BankingApp ..> BankDatabase : uses
    BankingApp ..> NotificationService : uses
    BankingApp ..> InterestCalculator : uses
```

## Description

Ce diagramme de classes représente l'architecture de l'application bancaire Java. L'application est organisée autour des entités principales suivantes :

- **Customer** : Représente un client de la banque avec ses informations personnelles et ses comptes
- **Account** : Représente un compte bancaire avec les opérations de base (dépôt, retrait, transfert)
- **Transaction** : Enregistre toutes les transactions effectuées sur les comptes
- **Address** : Encapsule les informations d'adresse du client
- **BankDatabase** : Service statique pour la gestion des données clients et comptes
- **NotificationService** : Service de notifications (email, SMS)
- **InterestCalculator** : Service de calcul et application des intérêts
- **BankingApp** : Classe principale d'application

Les énumérations **AccountType** et **TransactionType** définissent respectivement les types de comptes et de transactions supportés.
