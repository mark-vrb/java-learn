package threading.core;

import java.util.concurrent.Semaphore;

/**
 * Class for Pocket of Client.
 * Created by Mark Varabyou on 12/25/13.
 */
public class Pocket {
    private int amount = 0;
    private Semaphore watcherSemaphore;

    public Pocket(int amount) {
        this.amount = amount;
        watcherSemaphore = new Semaphore(1, true);
    }

    public boolean take(int amount) {
        try {
            watcherSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (this.amount < amount) {
            return false;
        }
        this.amount -= amount;

        watcherSemaphore.release();
        return true;
    }

    public void put(int amount) {
        try {
            watcherSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.amount += amount;
        watcherSemaphore.release();
    }

    public int check() {
        return amount;
    }

    public void watcherStart() {
        try {
            watcherSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void watcherEnd() {
        watcherSemaphore.release();
    }
}
