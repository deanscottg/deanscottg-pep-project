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
        Account checkedAccount = accountDAO.getAccountByUserName(account.getUsername());
        if(checkedAccount != null || account.password.length() < 4 || account.password.length() > 255 || account.username.length() < 1){
            return null;
        }
        else {
            Account addedAccount = accountDAO.insertAccount(account);
            return addedAccount;
        }
   
    }
    public Account verifyAccount(Account account){
        return accountDAO.verifyAccount(account);
    } 
}
