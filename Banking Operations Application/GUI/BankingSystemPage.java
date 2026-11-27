package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import File.*;

public class BankingSystemPage extends JFrame implements ActionListener {
    Font font15 = new Font("Consolas", Font.BOLD, 15);
    
    
    JLabel title, userLabel, nameLabel, addressLabel, phoneLabel, emailLabel;
    JTextField nameTextField, addressTextField, phoneTextField, emailTextField;
    JButton createUserButton, clearUserButton;
    
    ImageIcon img; 
    
    
    JLabel accountLabel, accountNoLabel, accountTypeLabel, balanceLabel, userAccountLabel;
    JTextField accountNoTextField, accountTypeTextField, balanceTextField, userAccountTextField;
    JButton createAccountButton, depositButton, withdrawButton, clearAccountButton;
    
    
    JLabel loanLabel, loanIdLabel, loanAmountLabel, interestRateLabel, termLabel, accountLoanLabel;
    JTextField loanIdTextField, loanAmountTextField, interestRateTextField, termTextField, accountLoanTextField;
    JButton createLoanButton, calculateButton, clearLoanButton;
    
    
    JTextArea screen;
    JButton showAllButton, saveButton, exportToTextButton;
    JTextField searchTextField;
    JButton searchButton;
   
    public Account[] accounts = new Account[100];
    public Loan[] loans = new Loan[100];
    public User[] users = new User[100];

    public BankingSystemPage() {
        super("Banking System");
        this.setSize(900, 950); 
        this.setLocation(200, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true); 
        this.setLayout(null);

        
        img = new ImageIcon("Image/money.jpg"); 
        this.setIconImage(img.getImage());
        
        
        FileIO.loadFromFile(accounts, loans, users, "bankdata.dat");
        
        
        initializeDefaultData();

       
        title = createLabel(10, 10, 300, 30, "Banking  System");
        
        
        userLabel = createLabel(10, 50, 150, 30, "Create New User");
        nameLabel = createLabel(10, 90, 150, 30, "Full Name:");
        nameTextField = createTextField(160, 90, 200, 30, "");
        addressLabel = createLabel(10, 130, 150, 30, "Address:");
        addressTextField = createTextField(160, 130, 200, 30, "");
        phoneLabel = createLabel(10, 170, 150, 30, "Phone:");
        phoneTextField = createTextField(160, 170, 200, 30, "");
        emailLabel = createLabel(10, 210, 150, 30, "Email:");
        emailTextField = createTextField(160, 210, 200, 30, "");
        
        createUserButton = createButton(10, 250, 200, 30, "Create User");
        clearUserButton = createButton(220, 250, 140, 30, "Clear");
        clearUserButton.setBackground(Color.ORANGE);
        
   
        accountLabel = createLabel(10, 300, 150, 30, "Account Management");
        accountNoLabel = createLabel(10, 340, 150, 30, "Account No:");
        accountNoTextField = createTextField(160, 340, 200, 30, "");
        accountTypeLabel = createLabel(10, 380, 150, 30, "Account Type:");
        accountTypeTextField = createTextField(160, 380, 200, 30, "");
        balanceLabel = createLabel(10, 420, 150, 30, "Initial Balance:");
        balanceTextField = createTextField(160, 420, 200, 30, "");
        userAccountLabel = createLabel(10, 460, 150, 30, "User ID:"); // This is the user's index
        userAccountTextField = createTextField(160, 460, 200, 30, "");
        
        createAccountButton = createButton(10, 500, 140, 30, "Create Account");
        depositButton = createButton(160, 500, 100, 30, "Deposit");
        withdrawButton = createButton(270, 500, 90, 30, "Withdraw");
        depositButton.setBackground(new Color(66, 179, 255));
        withdrawButton.setBackground(new Color(66, 179, 255));
        clearAccountButton = createButton(220, 540, 140, 30, "Clear");
        clearAccountButton.setBackground(Color.ORANGE);
        
       
        loanLabel = createLabel(10, 590, 150, 30, "Loan Management");
        loanIdLabel = createLabel(10, 630, 150, 30, "Loan ID:");
        loanIdTextField = createTextField(160, 630, 200, 30, "");
        loanAmountLabel = createLabel(10, 670, 150, 30, "Amount:");
        loanAmountTextField = createTextField(160, 670, 200, 30, "");
        interestRateLabel = createLabel(10, 710, 150, 30, "Interest Rate:");
        interestRateTextField = createTextField(160, 710, 200, 30, "");
        termLabel = createLabel(10, 750, 150, 30, "Term (months):");
        termTextField = createTextField(160, 750, 200, 30, "");
        accountLoanLabel = createLabel(10, 790, 150, 30, "Account No:");
        accountLoanTextField = createTextField(160, 790, 200, 30, "");
        
        createLoanButton = createButton(10, 830, 140, 30, "Create Loan");
        calculateButton = createButton(160, 830, 100, 30, "Calculate");
        clearLoanButton = createButton(270, 830, 90, 30, "Clear");
        calculateButton.setBackground(new Color(66, 179, 255));
        clearLoanButton.setBackground(Color.ORANGE);
        
        
        screen = new JTextArea();
        screen.setFont(font15);
        screen.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(screen);
        scrollPane.setBounds(400, 50, 470, 600);
        this.add(scrollPane);
        
        searchTextField = createTextField(400, 10, 200, 30, "");
        searchButton = createButton(610, 10, 120, 30, "Search");
        showAllButton = createButton(740, 10, 130, 30, "Show All");
        saveButton = createButton(400, 660, 470, 30, "Save Data (Binary)");
        saveButton.setBackground(Color.GREEN);

        
        exportToTextButton = createButton(400, 700, 470, 30, "Export Data to Text Files"); 
        exportToTextButton.setBackground(new Color(255, 200, 0)); 
        
        this.getContentPane().setBackground(new Color(240, 240, 240));
        this.setVisible(true);

        updateScreen(); 
    }
    
    
    private JLabel createLabel(int x, int y, int w, int h, String text) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font15);
        this.add(label);
        return label;
    }
    
    private JTextField createTextField(int x, int y, int w, int h, String text) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, w, h);
        textField.setFont(font15);
        this.add(textField);
        return textField;
    }
    
    private JButton createButton(int x, int y, int w, int h, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setFont(font15);
        button.addActionListener(this);
        button.setBackground(new Color(66, 245, 179));
        this.add(button);
        return button;
    }
    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createUserButton) {
            createUser();
        } else if (e.getSource() == clearUserButton) {
            clearUserFields();
        } else if (e.getSource() == createAccountButton) {
            createAccount();
        } else if (e.getSource() == depositButton) {
            depositToAccount();
        } else if (e.getSource() == withdrawButton) {
            withdrawFromAccount();
        } else if (e.getSource() == clearAccountButton) {
            clearAccountFields();
        } else if (e.getSource() == createLoanButton) {
            createLoan();
        } else if (e.getSource() == calculateButton) {
            calculateLoan();
        } else if (e.getSource() == clearLoanButton) {
            clearLoanFields();
        } else if (e.getSource() == searchButton) {
            searchData();
        } else if (e.getSource() == showAllButton) {
            showAllData();
        } else if (e.getSource() == saveButton) {
            saveData();
        } else if (e.getSource() == exportToTextButton) {
            exportDataToTextFiles();
        }
    }
    
    private void createUser() {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        
        if (!name.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            for (int i = 0; i < users.length; i++) {
                if (users[i] == null) {
                    users[i] = new User(name, address, phone, email);
                    JOptionPane.showMessageDialog(this, "User created successfully with ID: " + i);
                    clearUserFields();
                    updateScreen();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Maximum users reached!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all user fields!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void createAccount() {
        String accountNo = accountNoTextField.getText();
        String accountType = accountTypeTextField.getText();
        String balanceStr = balanceTextField.getText();
        String userIdStr = userAccountTextField.getText();
        
        if (!accountNo.isEmpty() && !accountType.isEmpty() && !balanceStr.isEmpty() && !userIdStr.isEmpty()) {
            try {
                double balance = Double.parseDouble(balanceStr);
                int userId = Integer.parseInt(userIdStr);
                
                if (userId >= 0 && userId < users.length && users[userId] != null) { // Check valid user ID
                    for (int i = 0; i < accounts.length; i++) {
                        if (accounts[i] == null) {
                            accounts[i] = new Account(accountNo, accountType, balance, users[userId]);
                            JOptionPane.showMessageDialog(this, "Account created successfully!");
                            clearAccountFields();
                            updateScreen();
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Maximum accounts reached!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid or non-existent User ID! Please create a user first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format for balance or User ID!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all account fields!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void depositToAccount() {
        String accountNo = accountNoTextField.getText();
        String amountStr = balanceTextField.getText(); 
        
        if (!accountNo.isEmpty() && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Deposit amount must be positive!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Account account = findAccount(accountNo);
                
                if (account != null) {
                    account.deposit(amount);
                    JOptionPane.showMessageDialog(this, "Deposit successful! New balance: $" + String.format("%.2f", account.getBalance()));
                    updateScreen();
                } else {
                    JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter account number and amount!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void withdrawFromAccount() {
        String accountNo = accountNoTextField.getText();
        String amountStr = balanceTextField.getText(); 
        
        if (!accountNo.isEmpty() && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Withdrawal amount must be positive!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Account account = findAccount(accountNo);
                
                if (account != null) {
                    if (account.getBalance() >= amount) {
                        account.withdraw(amount);
                        JOptionPane.showMessageDialog(this, "Withdrawal successful! New balance: $" + String.format("%.2f", account.getBalance()));
                        updateScreen();
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter account number and amount!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void createLoan() {
        String loanId = loanIdTextField.getText();
        String amountStr = loanAmountTextField.getText();
        String rateStr = interestRateTextField.getText();
        String termStr = termTextField.getText();
        String accountNo = accountLoanTextField.getText();
        
        if (!loanId.isEmpty() && !amountStr.isEmpty() && !rateStr.isEmpty() && !termStr.isEmpty() && !accountNo.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                double rate = Double.parseDouble(rateStr);
                int term = Integer.parseInt(termStr);

                if (amount <= 0 || rate < 0 || term <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount, interest rate (>=0) and term must be positive!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Account account = findAccount(accountNo);
                
                if (account != null) {
                    for (int i = 0; i < loans.length; i++) {
                        if (loans[i] == null) {
                            loans[i] = new Loan(loanId, amount, rate, term, account);
                            JOptionPane.showMessageDialog(this, "Loan created successfully!");
                            clearLoanFields();
                            updateScreen();
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Maximum loans reached!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Account not found for loan! Please ensure the account exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format for amount, rate or term!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all loan fields!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void calculateLoan() {
        String amountStr = loanAmountTextField.getText();
        String rateStr = interestRateTextField.getText();
        String termStr = termTextField.getText();
        
        if (!amountStr.isEmpty() && !rateStr.isEmpty() && !termStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                double rate = Double.parseDouble(rateStr);
                int term = Integer.parseInt(termStr);
                
                if (amount <= 0 || rate < 0 || term <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount, interest rate (>=0) and term must be positive for calculation!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Loan tempLoan = new Loan("TEMP", amount, rate, term, null); // Account is null for calculation
                JOptionPane.showMessageDialog(this, 
                    "Estimated Monthly Payment: $" + String.format("%.2f", tempLoan.calculateMonthlyPayment()) +
                    "\nTotal Payment: $" + String.format("%.2f", tempLoan.calculateMonthlyPayment() * term),
                    "Loan Calculation", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill amount, rate and term for calculation!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void searchData() {
        String searchTerm = searchTextField.getText().toLowerCase();
        if (searchTerm.isEmpty()) {
            showAllData();
            return;
        }
        
        StringBuilder result = new StringBuilder();
        
        
        result.append("=== USERS ===\n");
        boolean userFound = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && (users[i].getName().toLowerCase().contains(searchTerm) || 
                                     String.valueOf(i).contains(searchTerm) || // Search by User ID
                                     users[i].getAddress().toLowerCase().contains(searchTerm) ||
                                     users[i].getPhone().toLowerCase().contains(searchTerm) ||
                                     users[i].getEmail().toLowerCase().contains(searchTerm))) {
                result.append("User ID: ").append(i).append("\n");
                result.append(users[i].toString()).append("\n\n");
                userFound = true;
            }
        }
        if (!userFound) result.append("No matching users found.\n\n");
        
       
        result.append("=== ACCOUNTS ===\n");
        boolean accountFound = false;
        for (Account account : accounts) {
            if (account != null && (account.getAccountNo().toLowerCase().contains(searchTerm) || 
                                     account.getAccountType().toLowerCase().contains(searchTerm) ||
                                     (account.getUser() != null && account.getUser().getName().toLowerCase().contains(searchTerm)))) { // Search by user name linked to account
                result.append(account.toString()).append("\n\n");
                accountFound = true;
            }
        }
        if (!accountFound) result.append("No matching accounts found.\n\n");
        
        
        result.append("=== LOANS ===\n");
        boolean loanFound = false;
        for (Loan loan : loans) {
            if (loan != null && (loan.getLoanId().toLowerCase().contains(searchTerm) || 
                                  (loan.getAccount() != null && loan.getAccount().getAccountNo().toLowerCase().contains(searchTerm)))) { // Search by loan ID or associated account no
                result.append(loan.toString()).append("\n\n");
                loanFound = true;
            }
        }
        if (!loanFound) result.append("No matching loans found.\n\n");
        
        screen.setText(result.toString());
    }
    
    private void showAllData() {
        StringBuilder allData = new StringBuilder();
        
        
        allData.append("=== ALL USERS ===\n");
        boolean anyUsers = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                allData.append("User ID: ").append(i).append("\n");
                allData.append(users[i].toString()).append("\n\n");
                anyUsers = true;
            }
        }
        if (!anyUsers) allData.append("No users registered.\n\n");
        
       
        allData.append("=== ALL ACCOUNTS ===\n");
        boolean anyAccounts = false;
        for (Account account : accounts) {
            if (account != null) {
                allData.append(account.toString()).append("\n\n");
                anyAccounts = true;
            }
        }
        if (!anyAccounts) allData.append("No accounts created.\n\n");
        
        
        allData.append("=== ALL LOANS ===\n");
        boolean anyLoans = false;
        for (Loan loan : loans) {
            if (loan != null) {
                allData.append(loan.toString()).append("\n\n");
                anyLoans = true;
            }
        }
        if (!anyLoans) allData.append("No loans issued.\n\n");
        
        screen.setText(allData.toString());
    }
    
    private void saveData() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to save all data (to binary file)?", 
            "Confirm Save", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            FileIO.saveToFile(accounts, loans, users, "bankdata.dat"); // Pass users array
            JOptionPane.showMessageDialog(this, "Data saved successfully to bankdata.dat!");
        }
    }

    private void exportDataToTextFiles() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to export all data to text files?",
                "Confirm Export", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            FileIO.saveUsersToTextFile(users, "users.txt");
            FileIO.saveAccountsToTextFile(accounts, "accounts.txt");
            FileIO.saveLoansToTextFile(loans, "loans.txt");
            JOptionPane.showMessageDialog(this, "Data successfully exported to users.txt, accounts.txt, and loans.txt!");
        }
    }
    
    private void updateScreen() {
        showAllData(); 
    }
    
    private void clearUserFields() {
        nameTextField.setText("");
        addressTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
    }
    
    private void clearAccountFields() {
        accountNoTextField.setText("");
        accountTypeTextField.setText("");
        balanceTextField.setText("");
        userAccountTextField.setText("");
    }
    
    private void clearLoanFields() {
        loanIdTextField.setText("");
        loanAmountTextField.setText("");
        interestRateTextField.setText("");
        termTextField.setText("");
        accountLoanTextField.setText("");
    }
    
    private Account findAccount(String accountNo) {
        for (Account account : accounts) {
            if (account != null && account.getAccountNo().equals(accountNo)) {
                return account;
            }
        }
        return null;
    }

    private void initializeDefaultData() {
      
        boolean dataExists = false;
        for (User u : users) {
            if (u != null) {
                dataExists = true;
                break;
            }
        }

        if (!dataExists) {
            
            users[0] = new User("Aria", "123 Main St", "555-1111", "aria@gmail.com");
            users[1] = new User("Rodoshi", "456 Oak Ave", "555-2222", "rodoshi@gmail.com");
            users[2] = new User("Koli", "789 Pine Ln", "555-3333", "koli@gmail.com");

           
            accounts[0] = new Account("24-58502-2", "Savings", 1500.00, users[0]); 
            accounts[1] = new Account("2458507-3", "Checking", 2500.00, users[1]); 
            accounts[2] = new Account("24-59503-2", "Savings", 500.00, users[2]);  

            loans[0] = new Loan("SLoan001", 10000.00, 5.0, 60, findAccount("24-58502-2")); 
            loans[1] = new Loan("CLoan002", 20000.00, 7.5, 48, findAccount("2458507-3")); 
            loans[2] = new Loan("HLoan003", 150000.00, 4.0, 360, findAccount("24-59503-2")); 

            JOptionPane.showMessageDialog(this, "Default user, account, and loan data initialized!");
        }
    }
}