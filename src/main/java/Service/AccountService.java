package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        if(account.username == null || account.password.length() < 4 || account.username.length() == 0){
            return null;
        }
        else {
            Account addedAccount = accountDAO.insertAccount(account);
            return addedAccount;
        }
        // if(account.username != null && account.password.length() >= 4){
        //     Account addedAccount = accountDAO.insertAccount(account);
        // return addedAccount;
        // }
        // else return null;
        
    }
}
