package threading.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import threading.core.enums.ThreadStatus;

/**
 * Daemon who watch for summary cash amount in all Accounts and Pockets in the Bank.
 * In case, if he noticed cheating, cries laud.
 * Created by Mark Varabyou on 12/16/13.
 */
public class Watcher implements Runnable {
    private int interval;
    private int totalAmount;
    private Bank bank;
    private ThreadStatus status = ThreadStatus.Running;
    private static final Logger logger = LogManager.getLogger("Watcher");

    public Watcher(Bank bank, int interval) {
        this.bank = bank;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (status == ThreadStatus.Running) {
            logger.debug(String.format("Start checking the Bank"));
            totalAmount = 0;

            for (Account account : bank.getAccounts()) {
                account.watcherStart();
            }
            for (Client client : bank.getClients()) {
                client.watcherStart();
            }

            for (Client client : bank.getClients()) {
                totalAmount += client.checkPocket();
                client.watcherEnd();
            }
            for (Account account : bank.getAccounts()) {
                totalAmount += account.check();
                account.watcherEnd();
            }

            if (totalAmount == bank.getCashAmount()) {
                logger.debug(String.format("Check for Total Amount in the Bank succeed. Amount = %d", totalAmount));
            } else {
                logger.debug(String.format("Check for Total Amount in the Bank failed. Expected %d, Actual %d",
                        bank.getCashAmount(), totalAmount));
            }

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        status = ThreadStatus.Stopped;
        logger.debug("Watcher stopped.");
    }

    public void interrupt() {
        status = ThreadStatus.Interrupting;
    }

    public boolean isStopped() {
        return status == ThreadStatus.Stopped;
    }
}
