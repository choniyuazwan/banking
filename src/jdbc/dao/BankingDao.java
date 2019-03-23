package jdbc.dao;

import jdbc.model.*;

public interface BankingDao {
    boolean addCustomer(Customer customer);
    boolean addAccount(Account account);
    boolean addWallet(Wallet wallet);
    boolean addWalletAccount(WalletAccount walletAccount);
    boolean addTransaction(Transaction transaction);
    boolean addTransactionType(TransactionType transactionType);
    Customer getLastCustomer();
    Customer login(String username, String password);
}
