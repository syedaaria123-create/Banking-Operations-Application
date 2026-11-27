package Entity;

public class Account {
    private String accountNo;
    private String accountType;
    private double balance;
    private User user;

    public Account(String accountNo, String accountType, double balance, User user) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
    }

    
    public String getAccountNo() { return accountNo; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public User getUser() { return user; }
    
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { balance -= amount; }
    
    @Override
    public String toString() {
        return "Account No: " + accountNo + "\nType: " + accountType + 
               "\nBalance: $" + balance + "\n" + user.toString();
    }
}