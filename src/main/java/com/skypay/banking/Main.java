package com.skypay.banking;
import com.skypay.banking.entities.Account;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Skypay Banking Test ===\n");
        Account account = new Account();

        System.out.println("Making deposit 1");
        account.deposit(1000, LocalDate.of(2012, 1, 10));
        System.out.println("Making deposit 2");
        account.deposit(2000, LocalDate.of(2012, 1, 13));
        System.out.println("Making withdrawal");
        account.withdraw(500, LocalDate.of(2012, 1, 14));
        System.out.println("\n=== Bank Statement Table");
        account.printStatement();

        System.out.println("\nCurrent Balance: " + account.getBalance() );
    }
}