package Accounts;

import java.util.UUID;

public abstract class Account {
    private String accountId;
    protected AccountType accountType;
    protected double balance;
    private double interestRate;

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.accountId = UUID.randomUUID().toString();
        this.balance = 0.0;
        this.interestRate = 0.0;
    }

    public void insertMoney(double amount) {
        balance += amount;
    }

    public void withdrawMoney(double amount) {
        if (amount > balance) {
            return;
        }
        balance -= amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }


}
