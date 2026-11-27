package File;

import Entity.*;
import java.io.*;

public class FileIO {
   
    public static void saveToFile(Account[] accounts, Loan[] loans, User[] users, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(accounts);
            oos.writeObject(loans);
            oos.writeObject(users); 
            System.out.println("Data successfully saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data to " + filename + ": " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    
    public static void loadFromFile(Account[] accounts, Loan[] loans, User[] users, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Account[] loadedAccounts = (Account[]) ois.readObject();
            Loan[] loadedLoans = (Loan[]) ois.readObject();
            User[] loadedUsers = (User[]) ois.readObject();
            
            
            System.arraycopy(loadedAccounts, 0, accounts, 0, loadedAccounts.length);
            System.arraycopy(loadedLoans, 0, loans, 0, loadedLoans.length);
            System.arraycopy(loadedUsers, 0, users, 0, loadedUsers.length);
            System.out.println("Data successfully loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
           
        }
    }

    
    public static void saveUsersToTextFile(User[] users, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name,Address,Phone,Email\n"); 
            for (User user : users) {
                if (user != null) {
                    writer.write(String.format("%s,%s,%s,%s\n",
                            user.getName(),
                            user.getAddress(),
                            user.getPhone(),
                            user.getEmail()));
                }
            }
            System.out.println("User data successfully exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting user data to " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    public static void saveAccountsToTextFile(Account[] accounts, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("AccountNo,AccountType,Balance,UserName\n"); // Header row
            for (Account account : accounts) {
                if (account != null) {
                    writer.write(String.format("%s,%s,%.2f,%s\n",
                            account.getAccountNo(),
                            account.getAccountType(),
                            account.getBalance(),
                            account.getUser() != null ? account.getUser().getName() : "N/A")); // Include user's name
                }
            }
            System.out.println("Account data successfully exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting account data to " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    public static void saveLoansToTextFile(Loan[] loans, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("LoanId,Amount,InterestRate,Term(months),AssociatedAccountNo\n"); // Header row
            for (Loan loan : loans) {
                if (loan != null) {
                    writer.write(String.format("%s,%.2f,%.2f,%d,%s\n",
                            loan.getLoanId(),
                            loan.getAmount(),
                            loan.getInterestRate(),
                            loan.getTerm(),
                            loan.getAccount() != null ? loan.getAccount().getAccountNo() : "N/A")); // Include associated account number
                }
            }
            System.out.println("Loan data successfully exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting loan data to " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}