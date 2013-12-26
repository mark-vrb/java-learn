package threading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import threading.core.Bank;

import java.util.Random;

/**
 * Bank test application. Just keep Bank running for 5 seconds.
 */
public class App {
    private static final Logger logger = LogManager.getLogger("App");

    public static void main(String[] args) {
        Bank bank = new Bank(2000); // Watcher interval - 2 sec

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            bank.addAccount(i, random.nextInt(200000));
        }

        String[] names = new String[]{"Oliver Flynn","Horton Britt","Malinda Sloan","Riley Mueller","Rosario Mckay",
                "Coleen Nunez","Ingram Maldonado","Leann Hansen","Fowler Rosario","Morse Martin","Melendez Golden",
                "Newman Mason","Hazel Aguilar","Benson Jackson","Olga Thornton","Nina Key","Joni Simon",
                "Taylor Hopkins","Matilda Wilder","Phillips Macias","Colon Albert","Eula Raymond","Mcintyre Boyd",
                "Rogers Jacobson","Farmer Crane","Karen Mullins","Joy Mckenzie","Nixon George","Susanna Tate",
                "Sweet Tyson","Sheena Logan","Aline Lucas","Wynn Hernandez","Mccray Hewitt","Simmons Aguirre",
                "Lancaster Tran","Jannie Johnston","Deena Stone","Owen Coleman","Maureen Munoz","Rosalie Santiago",
                "Minerva Rich","Shannon Lyons","Madeleine Russell","Foreman Sanford","Dean Snyder","Alejandra Hooper",
                "Boone Phillips","Paula Rodgers","Kristin Pittman"};

        for (int i = 0; i < 50; i++) {
            bank.addClient(i, names[i], random.nextInt(5000));
        }

        String[] cashiersNames = new String[]{"Dena Glenn","Nicholson Hanson","Osborn Watts","Mcbride Russell",
                "Shari Hudson","Kirk Cardenas","Sophia Hull","Monroe Craig","Tami Hickman","Minerva Simmons"};

        for (int i = 0; i < 10; i++) {
            bank.addCashier(i, cashiersNames[i]);
        }

        logger.debug("Starting the bank.");

        bank.start();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.debug("Time is up. Carefully stopping the Bank.");

        bank.stop();

        logger.debug("Bank stopped. Program finished.");
    }
}
