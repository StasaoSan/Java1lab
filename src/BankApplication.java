import Accounts.Account;
import Accounts.AccountFactory;
import Accounts.AccountType;
import Banks.ConcreateBank;
import CentralBank.CentralBank;
import UI.BaseUI;
import util.SimpleAccountObserver;

public class BankApplication {
    public static void main(String[] args) {
        BaseUI ui = new BaseUI();
        CentralBank CBank1 = new CentralBank();
        SimpleAccountObserver observer = new SimpleAccountObserver();
        ConcreateBank bank1 = ui.CreateBank(CBank1, observer);
        ConcreateBank bank2 = ui.CreateBank(CBank1, observer);
        AccountFactory accountFactory = new AccountFactory();

        Account account1 = ui.CreateAccount(accountFactory);
        Account account2 = ui.CreateAccount(accountFactory);
        bank1 = (ConcreateBank) ui.CreateClientAndAddToBank(account1, bank1);
        bank2 = (ConcreateBank) ui.CreateClientAndAddToBank(account2, bank2);


        account1.insertMoney(1000);
        account2.insertMoney(500);

        System.out.println("Balance 1 before transfer: " + account1.getBalance());
        System.out.println("Balance 2 before transfer: " + account2.getBalance());


        bank1.changeInterestRate(AccountType.Credit, 12.3);

        CBank1.transferBetweenBanks(bank1.getBankId(), bank2.getBankId(), account1.getAccountId(), account2.getAccountId(), 200);

        System.out.println("Balance 1 after transfer: " + account1.getBalance());
        System.out.println("Balance 2 after transfer: " + account2.getBalance());
    }
}