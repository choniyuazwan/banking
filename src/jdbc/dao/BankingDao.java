package jdbc.dao;

import jdbc.model.*;

import java.util.List;

public interface BankingDao {
    boolean addPrimaryAccount(int accountNumber, String username);
    boolean addPrimaryWallet(int walletNumber, String username);

    Customer login(String username, String password);
    boolean addCustomer(Customer customer);
    Customer getLastCustomer();
    //    Customer getOneCustomer(String username);

    List<Account> getAllAccount(int cif);
    boolean addAccount(Account account);
    boolean removeAccount(int accountNumber);
    Account getLastAccount();
    //    Account getOneAccount(int accountNumber);

    List<Wallet> getAllWallet(int cif);
    boolean addWallet(Wallet wallet);
    boolean removeWallet(int id);
    Wallet getLastWallet();

    List<WalletAccount> getAllWalletAccount(int walletId);
    boolean addWalletAccount(WalletAccount walletAccount);
    boolean removeWalletAccount(int accountNumber);


    boolean addTransaction(Transaction transaction);
//    boolean addTransactionType(TransactionType transactionType);
}
