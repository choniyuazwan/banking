package jdbc.dao;

import jdbc.db.DBUtil;
import jdbc.model.*;
import jdbc.db.DBConnection;

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
    public boolean addWallet(Wallet wallet) {
        return dbUtil.addWallet(wallet);
    }

    @Override
    public boolean addWalletAccount(WalletAccount walletAccount) {
        return dbUtil.addWalletAccount(walletAccount);
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        return dbUtil.addTransaction(transaction);
    }

    @Override
    public boolean addTransactionType(TransactionType transactionType) {
        return dbUtil.addTransactionType(transactionType);
    }

    @Override
    public Customer getLastCustomer() {
        return dbUtil.getLastCustomer();
    }

    @Override
    public Customer login(String username, String password) {
        return dbUtil.login(username, password);
    }

}