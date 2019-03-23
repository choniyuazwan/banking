package jdbc.db;

import jdbc.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    public DBUtil(Connection connect) {
        connection = connect;
    }

    public boolean addCustomer(Customer customer) {
        try {
            String sql = "INSERT INTO customer (firstName, lastName, birthDate, username, password) VALUE(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getBirthDate());
            preparedStatement.setString(4, customer.getUsername());
            preparedStatement.setString(5, customer.getPassword());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addAccount(Account account) {
        try {
            String sql = "INSERT INTO account (accountName, cif, balance) VALUE(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getAccountName());
            preparedStatement.setInt(2, account.getCif());
            preparedStatement.setInt(3, account.getBalance());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addWallet(Wallet wallet) {
        try {
            String sql = "INSERT INTO wallet (description) VALUE(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wallet.getDescription());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTransactionType(TransactionType transactionType) {
        try {
            String sql = "INSERT INTO transactionType (description) VALUE(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transactionType.getDescription());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addWalletAccount(WalletAccount walletAccount) {
        try {
            String sql = "INSERT INTO walletAccount (walletId, accountNumber) VALUE(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, walletAccount.getWalletId());
            preparedStatement.setInt(2, walletAccount.getAccountNumber());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTransaction(Transaction transaction) {
        try {
            String sql = "INSERT INTO transaction (accountNumberDebit, accountNumberCredit, amount, transactionType) VALUE(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transaction.getAccountNumberDebit());
            preparedStatement.setInt(2, transaction.getAccountNumberCredit());
            preparedStatement.setInt(3, transaction.getAmount());
            preparedStatement.setInt(4, transaction.getTransactionType());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer getLastCustomer() {
        Customer result = new Customer();
        try {
            String sql = "SELECT * FROM customer ORDER BY cif DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCif(resultSet.getInt("cif"));
                result = customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Customer login(String username, String password) {
        Customer result = null;
        try {
            String sql = "SELECT * FROM customer where username=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password"));
                result = customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
