package Accounts;

public class AccountFactory {
    public Account createAccount(AccountType type){
        switch (type){
            case Credit:
                double LIMIT = 500;
                return new CreditAccount(LIMIT);
            case Debit:
                return new DebitAccount();
            case Deposit:
                Integer TERM_DAYS = 30;
                return new DepositAccount(TERM_DAYS);
            default:
                throw new IllegalArgumentException("Unknown account type: " + type);
        }
    }
}
