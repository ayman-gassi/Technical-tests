package com.skypay.banking.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Account {
    private int balance;
    private ArrayList<Transaction> transactions;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

        public void deposit(int amount, LocalDate date) {
            if (amount <= 0) {throw new IllegalArgumentException("Deposit amount must be positive");}
            balance += amount;
            transactions.add(new Transaction(date, amount, balance));
        }

    public void withdraw(int amount, LocalDate date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
            }
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    public void printStatement() {
        System.out.println("DATE       | AMOUNT  | BALANCE");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i).format());
        }
    }

        public int getBalance() {
            return balance;
        }

    public int getTransactionCount() {
        return transactions.size();
    }
}
