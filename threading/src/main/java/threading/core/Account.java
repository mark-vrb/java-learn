package threading.core;

/**
 * Represents Account in th Bank system.
 * Operations with amount of money is synchronized.
 * Created by Mark Varabyou on 12/16/13.
 */
public class Account {
    private int id;
    private int amount;

    public Account(int id, int initialAmount) {
        this.id = id;
        this.amount = initialAmount;
    }

    public int getId() {
        return id;
    }

    public synchronized int check() {
        return amount;
    }

    public synchronized boolean tryWithdraw(int amount) {
        if (this.amount < amount)
            return false;
        this.amount -= amount;
        return true;
    }

    public synchronized void deposit(int amount) {
        this.amount += amount;
    }
}
