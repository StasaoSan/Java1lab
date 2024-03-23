package util;

import Accounts.AccountType;

public interface AccountSubject {
    void registerObserver(AccountObserver o);
    void removeObserver(AccountObserver o);
    void notifyObservers(AccountType accountType, String message);
}