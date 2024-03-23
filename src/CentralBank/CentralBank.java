package CentralBank;

import Banks.BasicBank;

import java.util.HashMap;
import java.util.UUID;

public class CentralBank {
    private HashMap<String, BasicBank> banks;

    public CentralBank() {
        banks = new HashMap<String, BasicBank>();
    }

    public String registerBank(BasicBank basicBank) {
        String bankId = UUID.randomUUID().toString();
        banks.put(bankId, basicBank);
        return bankId;
    }

    public void removeBank(String bankId) {
        banks.remove(bankId);
    }

    public BasicBank findBankById(String bankId) {
        return banks.get(bankId);
    }

    public void transferBetweenBanks(String fromBankId, String toBankId, String fromAccountId, String toAccountId, double amount) {
        BasicBank fromBasicBank = banks.get(fromBankId);
        BasicBank toBasicBank = banks.get(toBankId);

        if (fromBasicBank != null && toBasicBank != null) {
            fromBasicBank.findAccount(fromAccountId).withdrawMoney(amount);
            toBasicBank.findAccount(toAccountId).insertMoney(amount);
            System.out.println("Transfered " + amount + " from " + fromAccountId + " to " + toAccountId);
        }
        else {System.out.println("One of the banks cannot be found.");}
    }
}
