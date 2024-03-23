package util;

import Accounts.AccountType;

public class SimpleAccountObserver implements AccountObserver {
    @Override
    public void update(AccountType accountType, String message) {
        System.out.println("Обновление для типа счета " + accountType + ": " + message);
    }
}
