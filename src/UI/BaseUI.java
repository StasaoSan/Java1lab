package UI;

import Accounts.Account;
import Accounts.AccountFactory;
import Accounts.AccountType;
import Banks.BasicBank;
import Banks.ConcreateBank;
import CentralBank.CentralBank;
import Clients.Client;
import util.AccountObserver;

import java.util.Objects;
import java.util.Scanner;

public class BaseUI {
    public ConcreateBank CreateBank(CentralBank centralBank, AccountObserver observer){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в систему регистрации банков.");
        System.out.println("Введите имя банка:");
        String name = scanner.nextLine();

        ConcreateBank bank = new ConcreateBank(name);
        bank.registerObserver(observer);
        bank.setBankId(centralBank.registerBank(bank));
        return bank;
    }

    public Account CreateAccount(AccountFactory factory){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в систему регистрации аккаунита.");
        System.out.println("Выберите тип счета (1 - Дебетовый, 2 - Кредитный, 3 - Депозитный): ");
        int accountChoice = scanner.nextInt();

        AccountType accountType = null;
        switch (accountChoice) {
            case 1:
                accountType = AccountType.Debit;
                break;
            case 2:
                accountType = AccountType.Credit;
                break;
            case 3:
                accountType = AccountType.Deposit;
                break;
            default:
                System.out.println("Неверный выбор.");
                return null;
        }

        return factory.createAccount(accountType);
    }

    public BasicBank CreateClientAndAddToBank(Account account, BasicBank basicBank){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите вашу фамилию: ");
        String surname = scanner.nextLine();

        Client client = new Client(name, surname);

        System.out.print("Вы создали аккаунт. Для его использования необходимо заполнить дополнительную информацию. Продолжить (y)?: ");
        if (Objects.equals(scanner.nextLine(), "y")){
            System.out.print("Введите номер вашего паспорта: ");
            String numPassport = scanner.nextLine();
            client.setNumPassport(Integer.valueOf(numPassport));

            System.out.print("Введите адрес: ");
            String address = scanner.nextLine();
            client.setAddress(address);
        }
        basicBank.addClient(client);
        basicBank.addAccount(client.getClientId(), account);
        return basicBank;
    }
}
