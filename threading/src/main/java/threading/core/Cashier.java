package threading.core;

/**
 * Class represents Cashier, the Bank employee, who performs operations with accounts, requested by Clients.
 * Created by Mark Varabyou on 12/16/13.
 */
public class Cashier extends Person {
    public Cashier(int id, String name, Bank bank) {
        super(id, name, bank);
    }

    public int checkAmount(Account account) {
        return account.check();
    }

    public int takeAmount(int amount, Account account) {
        if (account.tryWithdraw(amount))
            return amount;
        return 0;
    }

    public void putAmount(int amount, Account account) {
        account.deposit(amount);
    }

    public boolean transferAmount(int amount, Account source, Account destination) {
        if (source.tryWithdraw(amount)) {
            destination.deposit(amount);
            return true;
        }
        return false;
    }
}
