package jdbc.dao;

import jdbc.db.DBUtil;
import jdbc.model.*;
import jdbc.db.DBConnection;

import java.util.List;

public class BankingDaoImpl implements BankingDao {
    private DBUtil dbUtil;

    public BankingDaoImpl() {
        dbUtil = new DBUtil(DBConnection.getConnection());
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return dbUtil.addCustomer(customer);
    }

    @Override
    public boolean addAccount(Account account) {
        return dbUtil.addAccount(account);
    }

    @Override
    public boolean removeAccount(int accountNumber) {
        return dbUtil.removeAccount(accountNumber);
    }

    @Override
    public boolean addWallet(Wallet wallet) {
        return dbUtil.addWallet(wallet);
    }

    @Override
    public boolean removeWallet(int id) {
        return dbUtil.removeWallet(id);
    }

    @Override
    public boolean addWalletAccount(WalletAccount walletAccount) {
        return dbUtil.addWalletAccount(walletAccount);
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        return dbUtil.addTransaction(transaction);
    }

//    @Override
//    public boolean addTransactionType(TransactionType transactionType) {
//        return dbUtil.addTransactionType(transactionType);
//    }

    @Override
    public Customer getLastCustomer() {
        return dbUtil.getLastCustomer();
    }

    @Override
    public Account getLastAccount() {
        return dbUtil.getLastAccount();
    }

    @Override
    public List<Wallet> getAllWallet(int cif) {
        return dbUtil.getAllWallet(cif);
    }

    @Override
    public Wallet getLastWallet() {
        return dbUtil.getLastWallet();
    }

    @Override
    public Customer login(String username, String password) {
        return dbUtil.login(username, password);
    }

//    @Override
//    public Customer getOneCustomer(String username) {
//        return dbUtil.getOneCustomer(username);
//    }

    @Override
    public boolean addPrimaryAccount(int accountNumber, String username) {
        return dbUtil.addPrimaryAccount(accountNumber, username);
    }

    @Override
    public boolean addPrimaryWallet(int walletNumber, String username) {
        return dbUtil.addPrimaryWallet(walletNumber, username);
    }

    @Override
    public List<Account> getAllAccount(int cif) {
        return dbUtil.getAllAccount(cif);
    }

//    @Override
//    public Account getOneAccount(int accountNumber) {
//        return dbUtil.getOneAccount(accountNumber);
//    }
}
