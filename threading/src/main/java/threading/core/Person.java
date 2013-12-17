package threading.core;

/**
 * Abstract class for Persons from the Bank point of view.
 * Created by Mark Varabyou on 12/16/13.
 */
public abstract class Person {
    private int id;
    private String name;
    protected Bank bank;

    public Person(int id, String name, Bank bank) {
        this.id = id;
        this.name = name;
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
