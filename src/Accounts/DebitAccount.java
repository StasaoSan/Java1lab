package Accounts;

public class DebitAccount extends Account {
    public DebitAccount() {
        super(AccountType.Debit);
    }

    @Override
    public void withdrawMoney(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds on your account");
        }
    }
}
