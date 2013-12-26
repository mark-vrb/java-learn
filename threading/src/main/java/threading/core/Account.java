package threading.core;

import java.util.concurrent.Semaphore;

/**
 * Represents Account in th Bank system.
 * Operations with amount of money is synchronized.
 * Created by Mark Varabyou on 12/16/13.
 */
public class Account {
    private int id;
    private int amount;
    private Semaphore semaphore;
    private Semaphore watcherSemaphore;

    public Account(int id, int initialAmount) {
        this.id = id;
        this.amount = initialAmount;
        this.semaphore = new Semaphore(1, true);
        this.watcherSemaphore = new Semaphore(1, true);
    }

    public void acquireAccount() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseAccount() {
        semaphore.release();
    }

    public int getId() {
        return id;
    }

    public int check() {
        return amount;
    }

    public boolean tryWithdraw(int amount) {
        try {
            watcherSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (this.amount < amount)
            return false;
        this.amount -= amount;

        watcherSemaphore.release();
        return true;
    }

    public void deposit(int amount) {
        try {
            watcherSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount += amount;
        watcherSemaphore.release();
    }

    public Account getCopy() {
        return new Account(this.id, this.amount);
    }

    public void watcherStart() {
        try {
//            semaphore.acquire();
            watcherSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        semaphore.release();
    }

    public void watcherEnd() {
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        watcherSemaphore.release();
//        semaphore.release();
    }
}
