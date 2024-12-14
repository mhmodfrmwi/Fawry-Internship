package example.account;

public interface AccountManager {

    String deposit(Customer customer, int amount);

    String withdraw(Customer customer, int amount);

}
