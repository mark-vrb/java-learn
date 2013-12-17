package threading.core;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

/**
 * Represents queue for clients, waiting for free cashiers.
 * Created by Mark Varabyou on 12/17/13.
 */
public class CashierPool {
    private LinkedBlockingDeque<Cashier> freeCashiers;
    private Semaphore semaphore;

    public CashierPool(ArrayList<Cashier> cashiers) {
        freeCashiers = new LinkedBlockingDeque<Cashier>(cashiers);
        semaphore = new Semaphore(freeCashiers.size(), true);
    }

    public Cashier getCashier() throws InterruptedException {
        semaphore.acquire();
        return freeCashiers.poll();
    }

    public void releaseCashier(Cashier cashier) {
        freeCashiers.add(cashier);
        semaphore.release();
    }
}
