package Accounts;

public class DepositAccount extends Account {
    private int depositTermDays;
    private boolean canWithdraw;

    public DepositAccount(Integer depositTermDays) {
        super(AccountType.Deposit);
        this.depositTermDays = depositTermDays;
        this.canWithdraw = false;
    }

    public void checkTerm(Integer daysPassed) {
        if (daysPassed >= depositTermDays) {
            canWithdraw = true;
        }
    }

    @Override
    public void withdrawMoney(double amount) {
        if (canWithdraw && balance >= amount) {
            balance -= amount;
        } else {
            if (!canWithdraw) {
                System.out.println("Withdrawal is not allowed until the deposit term has ended.");
            } else {
                System.out.println("Insufficient funds on your deposit account.");
            }
        }
    }
}
