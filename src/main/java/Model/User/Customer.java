package Model.User;

import Model.Account.Account;

public class Customer extends User {
    public Customer(String userId, String userName, String password, String fullName, String address, String phoneNum, double balance, Account account, String imageLocation) {
        super(userId, userName, password, fullName, address, phoneNum, balance, account, imageLocation);
    }

}
