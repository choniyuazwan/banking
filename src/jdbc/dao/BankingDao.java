package jdbc.dao;

import jdbc.model.*;

import java.util.List;

public interface BankingDao {
    boolean addCustomer(Customer customer);
    boolean addAccount(Account account);
    boolean addWallet(Wallet wallet);
    boolean addWalletAccount(WalletAccount walletAccount);
    boolean addTransaction(Transaction transaction);
    boolean addTransactionType(TransactionType transactionType);
    Customer getLastCustomer();
    Account getLastAccount();
    Wallet getLastWallet();
    Customer login(String username, String password);
    Customer getOneCustomer(String username);
    boolean addPrimaryAccount(int accountNumber, String username);
    boolean addPrimaryWallet(int walletNumber, String username);
    List<Account> getAllAccount(int cif);
}
