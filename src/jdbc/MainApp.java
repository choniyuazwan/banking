package jdbc;

import jdbc.dao.BankingDao;
import jdbc.dao.BankingDaoImpl;
import jdbc.model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    static BankingDao bankingDao = new BankingDaoImpl();

    static Customer customer = new Customer();
    static Account account = new Account();
    static Wallet wallet = new Wallet();
    static WalletAccount walletAccount = new WalletAccount();
    static TransactionType transactionType = new TransactionType();
    static Transaction transaction = new Transaction();

    public static void main(String[] args) {
        menu1();
    }

    static void menu1() {
        System.out.println("\n========= BANKING =========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Quit");
        System.out.println("");
        System.out.print("Choose menu: ");

        try {
            String choice = input.readLine().trim();

            if (choice.equals("0")) {
                System.out.println("Terimakasih");
                System.exit(0);
            } else if (choice.equals("1")) {
                addCustomer();
                int cif = bankingDao.getLastCustomer().getCif();
                addAccount(cif);
                int accountNumber = bankingDao.getLastAccount().getAccountNumber();
                bankingDao.addPrimaryAccount(accountNumber, customer.getUsername());
                addWallet(cif);
                int walletId = bankingDao.getLastWallet().getId();
                bankingDao.addPrimaryWallet(walletId, customer.getUsername());
                menu2();
            } else if (choice.equals("2")) {
                if(login()) {
                    System.out.println("Login success");
                    menu2();
                } else {
                    System.out.println("Login failed");
                    menu1();
                }
            } else {
                System.out.println("Wrong choice");
                menu1();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void menu2() {
        System.out.println("\n========= BANKING =========");
        System.out.println("1. Account");
        System.out.println("2. Wallet");
        System.out.println("3. Wallet Account");
        System.out.println("4. Transaction");
        System.out.println("5. Profile");
        System.out.println("0. Quit");
        System.out.println("");
        System.out.print("Choose menu: ");

        try {
            String choice = input.readLine().trim();

            if (choice.equals("0")) {
                System.out.println("Terimakasih");
                System.exit(0);
            } else if (choice.equals("1")) {
                account();
                menu2();
            } else if (choice.equals("2")) {
                wallet();
                menu2();
            } else if (choice.equals("3")) {
                walletAccount();
                menu2();
            } else if (choice.equals("4")) {
                transaction();
                menu2();
            } else if (choice.equals("5")) {
                profile();
                menu2();
            } else {
                System.out.println("Wrong choice");
                menu2();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean login() {
        try {
            System.out.println("Login");
            System.out.print("Username: ");
            customer.setUsername(input.readLine().trim());
            System.out.print("Password: ");
            customer.setPassword(input.readLine().trim());
            customer = bankingDao.login(customer.getUsername(), customer.getPassword());
            if(customer != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static void transaction() {
        System.out.println("\n========= Transaction =========");
        System.out.println("1. See History");
        System.out.println("2. Top Up");
        System.out.println("3. Transfer");
        System.out.println("4. Withdraw");
        System.out.println("0. Main Menu");
        System.out.println("");
        System.out.print("Choose menu: ");

        try {
            String choice = input.readLine().trim();

            if (choice.equals("0")) {
                menu2();
                System.exit(0);
            } else if (choice.equals("1")) {
                transaction();
            } else if (choice.equals("2")) {
                topUp();
                transaction();
            } else if (choice.equals("3")) {
                transfer();
                transaction();
            } else if (choice.equals("4")) {
                withdraw();
                transaction();
            } else {
                System.out.println("Wrong choice");
                transaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void topUp() {
        try {
            System.out.println("Top Up");
            System.out.println("Your Account Kredit");
//            System.out.println("cif customer= "+customer.getCif());
            List<Account> listAllAccount = bankingDao.getAllAccount(customer.getCif());
            printAllAccount(listAllAccount);
            System.out.println("Fill account number: ");
            transaction.setAccountNumberCredit(Integer.parseInt(input.readLine()));
            System.out.print("Amount: ");
            transaction.setAmount(Integer.parseInt(input.readLine()));
            transaction.setTransactionType(1);
            if(bankingDao.addTransaction(transaction)) {
                success();
            } else {
                failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void transfer() {

    }

    static void withdraw() {}

    static void account() {}

    static void wallet() {
        System.out.println("\n========= WALLET =========");
        System.out.println("1. See Account");
        System.out.println("2. Add Account");
        System.out.println("3. Delete Account");
        System.out.println("0. Main Menu");
        System.out.println("");
        System.out.print("Choose menu: ");

        try {
            String choice = input.readLine().trim();

            if (choice.equals("0")) {
                menu2();
                System.exit(0);
            } else if (choice.equals("1")) {
                wallet();
            }  else {
                System.out.println("Wrong choice");
                wallet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void walletAccount() {}

    static void transactionType() {}

    static void profile() {}

    static void addCustomer() {
        try {
            System.out.println("Register Customer");
            System.out.print("First Name: ");
            customer.setFirstName(input.readLine().trim());
            System.out.print("Last Name: ");
            customer.setLastName(input.readLine().trim());
            System.out.print("Birth Date: ");
            customer.setBirthDate(input.readLine().trim());
            System.out.print("Username: ");
            customer.setUsername(input.readLine().trim());
            System.out.print("Password: ");
            customer.setPassword(input.readLine().trim());
            if(bankingDao.addCustomer(customer)) {
                success();
            } else {
                failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addAccount(int cif) {
        try {
            System.out.println("Add Account");
            System.out.print("Account Name: ");
            account.setAccountName(input.readLine().trim());
            System.out.print("Initial Deposit: ");
            account.setBalance(Integer.parseInt(input.readLine()));
            account.setCif(cif);
            if(bankingDao.addAccount(account)) {
                success();
            } else {
                failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addWallet(int cif) {
        try {
            System.out.println("Add Wallet");
            System.out.print("Description: ");
            wallet.setDescription(input.readLine().trim());
            wallet.setCif(cif);
            if(bankingDao.addWallet(wallet)) {
                success();
            } else {
                failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addTransactionType() {
        try {
            System.out.println("Add Transaction Type");
            System.out.print("Description: ");
            transactionType.setDescription(input.readLine().trim());
            if(bankingDao.addTransactionType(transactionType)) {
                success();
            } else {
                failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addWalletAccount() {
        try {
            System.out.println("Add Wallet Account");
            System.out.print("Wallet Id: ");
            walletAccount.setWalletId(Integer.parseInt(input.readLine()));
            System.out.print("Account Number: ");
            walletAccount.setAccountNumber(Integer.parseInt(input.readLine()));

            if(bankingDao.addWalletAccount(walletAccount)) {
                success();
            } else {
                failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void success() {
        System.out.println("Proccess success");
    }

    static void failed() {
        System.out.println("Process failed");
    }

    static void notFound() {
        System.out.println("Data not found");
    }

    static void printAllAccount(List<Account> listAccount) {
        for (Account account : listAccount) {
            System.out.println(account.getAccountNumber() +  " | " + account.getAccountName() + " |");
        }
    }
}
