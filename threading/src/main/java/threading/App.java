package threading;

import threading.core.Bank;

/**
 * Bank test application. Just keep Bank running for 5 seconds.
 */
public class App {
    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.addAccount(1, 1000);
        bank.addAccount(2, 3400);
        bank.addAccount(3, 50000);
        bank.addAccount(4, 6000);
        bank.addAccount(5, 78000);
        bank.addAccount(6, 300);
        bank.addAccount(7, 23000);
        bank.addAccount(8, 390000);

        bank.addClient(1, "Jacob", 2000);
        bank.addClient(2, "Ava", 4000);
        bank.addClient(3, "Michael", 1000);
        bank.addClient(4, "Liam", 5000);
        bank.addClient(5, "Abigail", 35000);

        bank.addCashier(1, "Sophia");
        bank.addCashier(2, "Emily");
        bank.addCashier(3, "Mia");

        bank.start();

        System.out.println("Started!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5 seconds is gone!");

        bank.stop();

        System.out.println("Stopped!");
        System.out.println("Bye!");
    }
}
