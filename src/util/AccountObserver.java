package util;

import Accounts.AccountType;

public interface AccountObserver {
    void update(AccountType accountType, String message);
}
