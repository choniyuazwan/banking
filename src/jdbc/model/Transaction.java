package jdbc.model;

public class Transaction {
    private int id, accountNumberDebit, accountNumberCredit, amount, transactionType;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNumberDebit() {
        return accountNumberDebit;
    }

    public void setAccountNumberDebit(int accountNumberDebit) {
        this.accountNumberDebit = accountNumberDebit;
    }

    public int getAccountNumberCredit() {
        return accountNumberCredit;
    }

    public void setAccountNumberCredit(int accountNumberCredit) {
        this.accountNumberCredit = accountNumberCredit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
