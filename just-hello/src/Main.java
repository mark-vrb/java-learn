import org.markvarabyou.justhello.core.Core;

public class Main {

    public static void main(String[] args) {
        Core core = new Core();
        System.out.println(String.format("Greetings from another package: '%s'", core.getGreeting()));
    }
}
