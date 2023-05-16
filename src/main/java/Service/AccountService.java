package Service;

import DataAccess.DataAccess;
import Model.Account.Account;

import java.util.ArrayList;
import java.util.Map;

public class AccountService {
    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> arrayAccounts = new ArrayList<>();
        for(Map.Entry<String, Account> account : DataAccess.getAllAccounts().entrySet()){
            arrayAccounts.add(account.getValue());
        }
        return arrayAccounts;
    }
}
