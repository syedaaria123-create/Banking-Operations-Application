package Entity;

public class Loan {
    private String loanId;
    private double amount;
    private double interestRate;
    private int term; 
    private Account account;

    public Loan(String loanId, double amount, double interestRate, int term, Account account) {
        this.loanId = loanId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.term = term;
        this.account = account;
    }

  
    public String getLoanId() { return loanId; }
    public double getAmount() { return amount; }
    public double getInterestRate() { return interestRate; }
    public int getTerm() { return term; }
    public Account getAccount() { return account; }
    
    public double calculateMonthlyPayment() {
        double monthlyRate = interestRate / 12 / 100;
        return (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -term));
    }
    
    @Override
    public String toString() {
        return "Loan ID: " + loanId + "\nAmount: $" + amount + 
               "\nInterest Rate: " + interestRate + "%\nTerm: " + term + 
               " months\nMonthly Payment: $" + String.format("%.2f", calculateMonthlyPayment()) + 
               "\nAssociated Account:\n" + account.getAccountNo();
    }
}