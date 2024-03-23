package Transactions;
import java.util.UUID;

public interface TransactionCommand {
    boolean execute();
    boolean undo();
    String getTransactionId();
}


