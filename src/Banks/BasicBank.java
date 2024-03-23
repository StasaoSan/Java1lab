package Banks;

import Accounts.AccountType;
import Clients.Client;
import Accounts.Account;
import Transactions.TransactionCommand;
import util.AccountObserver;

import util.AccountSubject;

import java.util.*;

public abstract class BasicBank implements AccountSubject {
    private String name;
    private String bankId;
    protected HashMap<String, Client> clients;
    protected HashMap<String, Account> accounts;
    protected List<TransactionCommand> transactionLog = new ArrayList<>();
    protected List<AccountObserver> observers = new ArrayList<>();
    protected Map<AccountType, Double> interestRates = new HashMap<>();
    public BasicBank(String name) {
        this.name = name;
        this.clients = new HashMap<>();
        this.accounts = new HashMap<>();
        interestRates.put(AccountType.Credit, 0.1);
        interestRates.put(AccountType.Debit, 0.05);
    }


    public void addClient(Client client) {
        clients.put(client.getClientId(), client);
    }

    public void deleteClient(String clientId) {
        clients.remove(clientId);
    }

    public void addAccount(String clientId, Account account) {
        if (!clients.containsKey(clientId)) {
            System.out.println("Client not found");
            return;
        }
        accounts.put(account.getAccountId(), account);
    }

    public void deleteAccount(String accountId) {
        accounts.remove(accountId);
    }

    public Account findAccount(String accountId) {
        return accounts.get(accountId);
    }

    public boolean transfer(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);
        if (fromAccount == null || toAccount == null) {
            System.out.println("One of the accounts not found");
            return false;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println("Insufficient funds");
            return false;
        }

        fromAccount.withdrawMoney(amount);
        toAccount.insertMoney(amount);
        return true;
    }

        public String getName() {
        return name;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        if (this.bankId == null) {
            this.bankId = bankId;
        } else {
            throw new IllegalStateException("Bank ID can only be set once.");
        }
    }

    public void printClients() {
        for (String clientId : clients.keySet()) {
            Client client = clients.get(clientId);
            System.out.println("Client ID: " + clientId + ", Name: " + client.getName() + " " + client.getSurname());
        }
    }

    public boolean executeTransaction(TransactionCommand command) {
        if (command.execute()) {
            transactionLog.add(command);
            return true;
        }
        return false;
    }

    public boolean undoTransaction(TransactionCommand command) {
        if (command.undo()) {
            transactionLog.remove(command);
            return true;
        }
        return false;
    }



    @Override
    public void registerObserver(AccountObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(AccountObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(AccountType accountType, String message) {
        for (AccountObserver observer : observers) {
            observer.update(accountType, message);
        }
    }

    public void changeInterestRate(AccountType accountType, double newRate) {
        interestRates.put(accountType, newRate);
        for (Account account : accounts.values()) {
            if (account.getAccountType() == accountType) {
                account.setInterestRate(newRate);
            }
        }
        notifyObservers(accountType, "New interest rate for " + accountType + ": " + newRate);
    }

}