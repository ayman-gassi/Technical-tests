import com.skypay.banking.entities.Account;

import java.time.LocalDate;

public class BankingTest {
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("=== Running Account Tests ===\n");

        testDeposit();
        testWithdraw();
        testInsufficientFunds();
        testNegativeDeposit();
        testNegativeWithdrawal();
        testMultipleTransactions();

        System.out.println("\n=== Test Results ===");
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
    }

    private static void testDeposit() {
        Account account = new Account();
        account.deposit(1000, LocalDate.now());

        if (account.getBalance() == 1000) {
            pass("testDeposit");
        } else {
            fail("testDeposit", "Expected balance 100000, got " + account.getBalance());
        }
    }

    private static void testWithdraw() {
        Account account = new Account();
        account.deposit(1000, LocalDate.now());
        account.withdraw(500, LocalDate.now());

        if (account.getBalance() == 500) {
            pass("testWithdraw");
        } else {
            fail("testWithdraw", "Expected balance 50000, got " + account.getBalance());
        }
    }

    private static void testInsufficientFunds() {
        Account account = new Account();
        account.deposit(1000, LocalDate.now());

        try {
            account.withdraw(1500, LocalDate.now());
            fail("testInsufficientFunds", "Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            pass("testInsufficientFunds");
        }
    }

    private static void testNegativeDeposit() {
        Account account = new Account();

        try {
            account.deposit(-100, LocalDate.now());
            fail("testNegativeDeposit", "Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            pass("testNegativeDeposit");
        }
    }

    private static void testNegativeWithdrawal() {
        Account account = new Account();
        account.deposit(1000, LocalDate.now());

        try {
            account.withdraw(-100, LocalDate.now());
            fail("testNegativeWithdrawal", "Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            pass("testNegativeWithdrawal");
        }
    }

    private static void testMultipleTransactions() {
        Account account = new Account();
        account.deposit(1000, LocalDate.of(2012, 1, 10));
        account.deposit(2000, LocalDate.of(2012, 1, 13));
        account.withdraw(500, LocalDate.of(2012, 1, 14));

        if (account.getBalance() == 2500 && account.getTransactionCount() == 3) {
            pass("testMultipleTransactions");
        } else {
            fail("testMultipleTransactions", "Expected balance 250000 with 3 transactions");
        }
    }

    private static void pass(String testName) {
        System.out.println("✓ " + testName + " PASSED");
        testsPassed++;
    }

    private static void fail(String testName, String message) {
        System.out.println("✗ " + testName + " FAILED: " + message);
        testsFailed++;
    }
}