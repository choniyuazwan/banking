package jdbc.db;

import jdbc.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    public DBUtil(Connection connect) {
        connection = connect;
    }


    //    customer
    public boolean addPrimaryAccount(int accountNumber, String username) {
        try {
            String sql = "UPDATE customer set primaryAccount=? where username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addPrimaryWallet(int walletNumber, String username) {
        try {
            String sql = "UPDATE customer set primaryWallet=? where username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, walletNumber);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
                customer.setCif(resultSet.getInt("cif"));
                customer.setFirstName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password"));
                customer.setPrimaryAccount(resultSet.getInt("primaryAccount"));
                customer.setPrimaryAccount(resultSet.getInt("primaryWallet"));
                customer.setBirthDate(resultSet.getString("birthDate"));
                result = customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Customer getOneCustomer(String username) {
//        Customer result = null;
//        try {
//            String sql = "SELECT * FROM customer where username=?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, username);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Customer customer = new Customer();
//                customer.setCif(resultSet.getInt("cif"));
//                customer.setUsername(resultSet.getString("firstName"));
//                customer.setUsername(resultSet.getString("lastName"));
//                customer.setUsername(resultSet.getString("birthDate"));
//                customer.setUsername(resultSet.getString("username"));
//                customer.setPassword(resultSet.getString("password"));
//                customer.setPrimaryAccount(resultSet.getInt("primaryAccount"));
//                result = customer;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


    //  account
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

    public Account getLastAccount() {
        Account result = new Account();
        try {
            String sql = "SELECT * FROM account ORDER BY accountNumber DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountNumber(resultSet.getInt("accountNumber"));
                result = account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Account> getAllAccount(int cif) {
        List<Account> listAccount = new ArrayList<Account>();
        try {
            String sql = "SELECT * FROM account where cif=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cif);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountNumber(resultSet.getInt("accountNumber"));
                account.setAccountName(resultSet.getString("accountName"));
                account.setBalance(resultSet.getInt("balance"));
                listAccount.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAccount;
    }

    public Account getOneAccount(int accountNumber) {
        Account result = null;
        try {
            String sql = "SELECT * FROM account where accountNumber=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account customer = new Account();
                customer.setAccountNumber(resultSet.getInt("accountNumber"));
                customer.setBalance(resultSet.getInt("balance"));
                result = customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean removeAccount(int accountNumber) {
        try {
            String sql = "delete from account where accountNumber=? and customer.primaryAccount";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //    wallet
    public List<Wallet> getAllWallet(int cif) {
        List<Wallet> listWallet = new ArrayList<Wallet>();
        try {
            String sql = "SELECT * FROM wallet where cif=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cif);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wallet wallet = new Wallet();
                wallet.setId(resultSet.getInt("id"));
                wallet.setDescription(resultSet.getString("description"));
                wallet.setCreatedDate(resultSet.getString("createdDate"));
                listWallet.add(wallet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listWallet;
    }

    public boolean addWallet(Wallet wallet) {
        try {
            String sql = "INSERT INTO wallet (description, cif) VALUE(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wallet.getDescription());
            preparedStatement.setInt(2, wallet.getCif());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeWallet(int id) {
        try {
            String sql = "delete from wallet where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Wallet getLastWallet() {
        Wallet result = new Wallet();
        try {
            String sql = "SELECT * FROM wallet ORDER BY id DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wallet wallet = new Wallet();
                wallet.setId(resultSet.getInt("id"));
                result = wallet;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    //    wallet account
    public List<WalletAccount> getAllWalletAccountCif(int cif) {
        List<Wallet> listWallet = new ArrayList<Wallet>();
        listWallet = getAllWallet(cif);

        List<WalletAccount> listWalletAccount = new ArrayList<WalletAccount>();
        for (Wallet wallet : listWallet) {
            try {
                String sql = "SELECT * FROM walletaccount where walletId=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, wallet.getId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    WalletAccount walletAccount = new WalletAccount();
                    walletAccount.setId(resultSet.getInt("id"));
                    walletAccount.setWalletId(resultSet.getInt("walletId"));
                    walletAccount.setAccountNumber(resultSet.getInt("accountNumber"));
                    listWalletAccount.add(walletAccount);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listWalletAccount;
    }

    public List<WalletAccount> getAllWalletAccount(int walletId) {
        List<WalletAccount> listWalletAccount = new ArrayList<WalletAccount>();
        try {
            String sql = "SELECT * FROM walletaccount where walletId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, walletId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WalletAccount walletAccount = new WalletAccount();
                walletAccount.setId(resultSet.getInt("id"));
                walletAccount.setWalletId(resultSet.getInt("walletId"));
                walletAccount.setAccountNumber(resultSet.getInt("accountNumber"));
                listWalletAccount.add(walletAccount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listWalletAccount;
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

    public boolean removeWalletAccount(int accountNumber) {
        try {
            String sql = "delete from walletaccount where accountNumber=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //    transaction
    public List<Transaction> getAllTransaction(int cif) {
        List<Account> listAccount = new ArrayList<Account>();
        listAccount = getAllAccount(cif);

        List<Transaction> listTransaction = new ArrayList<Transaction>();
        for (Account account : listAccount) {
            try {
                String sql = "SELECT * FROM transaction where accountNumberDebit=? or accountNumberCredit=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, account.getAccountNumber());
                preparedStatement.setInt(2, account.getAccountNumber());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setId(resultSet.getInt("id"));
                    transaction.setAccountNumberDebit(resultSet.getInt("accountNumberDebit"));
                    transaction.setAccountNumberCredit(resultSet.getInt("accountNumberCredit"));
                    transaction.setAmount(resultSet.getInt("amount"));
                    transaction.setTransactionType(resultSet.getInt("transactionType"));
                    transaction.setDate(resultSet.getString("date"));
                    listTransaction.add(transaction);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listTransaction;
    }
    public boolean addTransaction(Transaction transaction) {
        try {
            Account account = new Account();
            if (transaction.getTransactionType() == 2 || transaction.getTransactionType() == 3) {
                account = getOneAccount(transaction.getAccountNumberCredit());
            } else if (transaction.getTransactionType() == 1) {
                account = getOneAccount(transaction.getAccountNumberDebit());
            }

            if (account == null) {
                return false;
            }

            int oldBalance = account.getBalance();
            if (transaction.getTransactionType() == 2 || transaction.getTransactionType() == 3) {
                int newBalance = oldBalance - transaction.getAmount();
                account.setBalance(newBalance);
            } else if (transaction.getTransactionType() == 1) {
                int newBalance = oldBalance + transaction.getAmount();
                account.setBalance(newBalance);
            }

            String sql = "UPDATE account set balance=? where accountNumber=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.execute();

            sql = "INSERT INTO transaction (accountNumberDebit, accountNumberCredit, amount, transactionType) VALUE(?,?,?,?)";
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


    //    transaction type
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
}
