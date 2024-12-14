package example.store;

import example.account.AccountManager;
import example.account.Customer;

public class StoreImpl implements Store {

    AccountManager accountManager;

    public StoreImpl(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public String buy(Product product, Customer customer) {
        if (product.getQuantity() == 0) {
            return ("Product out of stock");
        }
        String status = accountManager.withdraw(customer, product.getPrice());
        if (!status.equals("success")) {
            return ("Payment failure: " + status);
        }
        product.setQuantity(product.getQuantity() - 1);
        return "success";
    }
}
