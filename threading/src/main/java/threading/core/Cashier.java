package threading.core;

/**
 * Class represents Cashier, the Bank employee, who performs operations with accounts, requested by Clients.
 * Created by Mark Varabyou on 12/16/13.
 */
public class Cashier extends Person {
    private Account lockedAccount;
    private Account workingCopy;

    public Cashier(int id, String name, Bank bank) {
        super(id, name, bank);
    }

    public boolean startWorkWithAccount(Account account) {
        if (this.lockedAccount != null)
            return false;
        account.acquireAccount();
        this.lockedAccount = account;
        this.workingCopy = lockedAccount.getCopy();
        return true;
    }

    public void putToAccount(int amount) {
        this.workingCopy.deposit(amount);
    }

    public void takeFromAccount(int amount) {
        this.workingCopy.tryWithdraw(amount);
    }

    public int checkAmountOnAccount() {
        return this.workingCopy.check();
    }

    public boolean commitWorkWithAccount() {
        boolean output = false;
        if (this.workingCopy.check() >= 0) {
            this.lockedAccount.deposit(this.workingCopy.check() - this.lockedAccount.check());
            output = true;
        }
        this.workingCopy = null;
        this.lockedAccount.releaseAccount();
        this.lockedAccount = null;
        return output;
    }
}
