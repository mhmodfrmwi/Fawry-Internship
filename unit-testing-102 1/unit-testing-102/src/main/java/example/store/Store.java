package example.store;


import example.account.Customer;

public interface Store {
    String buy(Product product, Customer customer);
}
