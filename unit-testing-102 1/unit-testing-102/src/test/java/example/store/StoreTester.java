package example.store;

import example.account.AccountManager;
import example.account.AccountManagerImpl;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTester {
    @Test
    public void testBuyWhenQuantityIsZeroThenItFails() {
        Store store=new StoreImpl(null);
        Customer customer=new Customer();
        Product product=new Product();
        product.setQuantity(0);
        store.buy(product,customer);
        String status=store.buy(product,customer);
        Assertions.assertEquals("Product out of stock",status);
    }
    @Test
    public void testBuyWhenQuantityIsNotZeroThenItPasses() {
        AccountManager accountManager=new AccountManagerImpl();
        Store store=new StoreImpl(accountManager);
        Customer customer=new Customer();
        Product product=new Product();
        product.setPrice(100);
        accountManager.deposit(customer,1000);
        product.setQuantity(10);
        String status=store.buy(product,customer);
        Assertions.assertEquals("success",status);
        Assertions.assertEquals(900,customer.getBalance());
        Assertions.assertEquals(9,product.getQuantity());
    }
    @Test
    public void testBuyWhenCustomerHasInsufficientBalanceThenItFails() {
        AccountManager accountManager=new AccountManagerImpl();
        Store store=new StoreImpl(accountManager);
        Customer customer=new Customer();
        Product product=new Product();
        product.setPrice(100);
        accountManager.deposit(customer,50);
        product.setQuantity(10);
        String status=store.buy(product,customer);
        Assertions.assertEquals("Payment failure: insufficient account balance",status);
        Assertions.assertEquals(50,customer.getBalance());
        Assertions.assertEquals(10,product.getQuantity());
    }
}
