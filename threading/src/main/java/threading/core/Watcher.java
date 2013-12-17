package threading.core;

/**
 * Daemon who watch for summary cash amount in all Accounts and Pockets in the Bank.
 * In case, if he noticed cheating, cries laud.
 * Created by Mark Varabyou on 12/16/13.
 */
public class Watcher {
    private Bank bank;

    public Watcher(Bank bank) {
        this.bank = bank;
    }
}
