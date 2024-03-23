package Transactions;

import Accounts.Account;
import Banks.BasicBank;
import java.util.UUID;

public class DepositCommand implements TransactionCommand {
    private final BasicBank basicBank;
    private final String accountId;
    private final double amount;
    private final String transactionId;

    public DepositCommand(BasicBank basicBank, String accountId, double amount) {
        this.basicBank = basicBank;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
    }

    @Override
    public boolean execute() {
        Account account = basicBank.findAccount(accountId);
        if (account != null) {
            account.insertMoney(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean undo() {
        Account account = basicBank.findAccount(accountId);
        if (account != null && account.getBalance() >= amount) {
            account.withdrawMoney(amount);
            return true;
        }
        return false;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }
}
