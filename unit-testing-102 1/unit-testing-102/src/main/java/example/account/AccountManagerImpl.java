package example.account;

public class AccountManagerImpl implements AccountManager {
    private static final int MAX_CREDIT = 1000;
    @Override
    public String deposit(Customer customer, int amount) {
        if (amount <= 0) {
            return "amount should be more than zero";
        }
        customer.setBalance(customer.getBalance() + amount);
        return "success";
    }
    @Override
    public String withdraw(Customer customer, int amount) {
        if (amount <= 0) {
            return "amount should be more than zero";
        }
        int expectedBalance = customer.getBalance() - amount;
        if (expectedBalance < 0) {
            if (!customer.isCreditAllowed()) {
                return "insufficient account balance";
            } else if (customer.isCreditAllowed()) {
                if (!customer.isVip()&&expectedBalance<-MAX_CREDIT) {
                    return "you have exceeded your credit limit";
                }
            }
        }
        customer.setBalance(expectedBalance);
        return "success";
    }
}
