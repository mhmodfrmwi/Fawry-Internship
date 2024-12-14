package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTester {
    AccountManager accountManager = new AccountManagerImpl();
    @Test
    public void testDepositWithPositiveAmount() {
        Customer customer = new Customer();
        String result=accountManager.deposit(customer, 100);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(100, customer.getBalance());
    }
    @Test
    public void testDepositWithNegativeAmount() {
        Customer customer = new Customer();
        String result=accountManager.deposit(customer, -100);
        Assertions.assertEquals("amount should be more than zero", result);
        Assertions.assertEquals(0, customer.getBalance());
    }
    @Test
    public void testWithdrawWithPositiveAmount () {
        Customer customer=new Customer();
        customer.setBalance(100);
        String result=accountManager.withdraw(customer, 100);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(0, customer.getBalance());
    }
    @Test
    public void testWithdrawWithNegativeAmount () {
        Customer customer=new Customer();
        customer.setBalance(100);
        String result=accountManager.withdraw(customer, -100);
        Assertions.assertEquals("amount should be more than zero", result);
        Assertions.assertEquals(100, customer.getBalance());
    }
    @Test
    public void testWithdrawWhenCustomerIsNotCreditAllowedSoItFails(){
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(false);
        String result=accountManager.withdraw(customer, 200);
        Assertions.assertEquals("insufficient account balance", result);
        Assertions.assertEquals(100, customer.getBalance());
    }
    @Test
    public void testWithdrawWhenCustomerIsCreditAllowedButNotEnoughBalanceSoItFails(){
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        String result=accountManager.withdraw(customer, 2000);
        Assertions.assertEquals("you have exceeded your credit limit", result);
        Assertions.assertEquals(100, customer.getBalance());
    }
    @Test
    public void testWithdrawWhenCustomerIsCreditAllowedAndEnoughBalanceSoItPasses(){
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        String result=accountManager.withdraw(customer, 300);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(100-300, customer.getBalance());
    }
    @Test
    public void testWithdrawWhenCustomerIsVip(){
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        customer.setVip(true);
        String result=accountManager.withdraw(customer, 3000);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(100-3000, customer.getBalance());
    }
}
