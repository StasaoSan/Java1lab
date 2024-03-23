package Accounts;

public class CreditAccount extends Account {
    private double creditLimit;
    private static final double USAGE_COMMISSION_PERCENT = 0.1;

    public CreditAccount(double creditLimit) {
        super(AccountType.Credit);
        this.creditLimit = creditLimit;
    }

    @Override
    public void insertMoney(double amount) {
        if (balance < 0)
            balance += amount * USAGE_COMMISSION_PERCENT;
        else {
            balance += amount;
        }
    }

    @Override
    public void withdrawMoney(double amount) {
        double effectiveAmount = amount;
        if (balance - amount < 0) {
            effectiveAmount += amount * USAGE_COMMISSION_PERCENT;
        }

        if (balance - effectiveAmount >= -creditLimit) {
            balance -= effectiveAmount;
        } else {
            System.out.println("Not enough credit limit and money on your account");
        }
    }
}
