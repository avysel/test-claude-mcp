# Documentation

Welcome to the Banking Application documentation directory.

## Overview

This directory contains comprehensive documentation for the banking application, including architectural diagrams, API documentation, and development guides.

## Contents

- [**Class Diagram**](class-diagram.md) - UML class diagram showing the application architecture and relationships between classes

## Architecture

The banking application follows a layered architecture with the following main components:

### Core Entities
- **Customer**: Manages customer information and accounts
- **Account**: Handles banking operations (deposits, withdrawals, transfers)  
- **Transaction**: Records all financial transactions
- **Address**: Stores customer address information

### Services
- **BankDatabase**: Centralized data management
- **NotificationService**: Email and SMS notifications
- **InterestCalculator**: Interest calculation and application

### Enumerations
- **AccountType**: CHECKING, SAVINGS, CREDIT, INVESTMENT
- **TransactionType**: DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT, REFUND, FEE, INTEREST

## Getting Started

1. Review the [Class Diagram](class-diagram.md) to understand the application structure
2. Explore the source code in the `src/com/example/banking/` directory
3. Run the main application from `BankingApp.java`

## Development Guidelines

- Follow conventional commit standards for all changes
- All modifications must go through Pull Request review
- Maintain documentation when adding new features
- Ensure proper test coverage for new functionality

## Contributing

1. Create a feature branch from `main`
2. Make your changes following coding standards
3. Update documentation if needed
4. Create a Pull Request for review
5. Wait for approval before merging

---

*This banking application is designed for educational and testing purposes.*