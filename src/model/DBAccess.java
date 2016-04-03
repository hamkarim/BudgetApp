
package model;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DBAccess {
    private static DBAccess database; 
    private static final String databaseDriver = "oracle.jdbc.driver.OracleDriver"; 
    final String HOSTINFO = "jdbc:oracle:thin:@dilbert.humber.ca:1521:grok"; 
    private static Connection connection;
    private static Statement statement;
    private static ResultSet userSet;
    private static ResultSet incomeSet; 
    private static ResultSet budgetSet; 
    private static ResultSet expenseSet;
    private static ResultSet expenseUserSet;
    
    
    private DBAccess() {}; 
    
    public static DBAccess getInstance() {
        if (database == null) {
            database = new DBAccess(); 
        }
        return database; 
    }
    
    public void establishConnection() throws Exception {
        // Step 1: Loading Driver
        Class.forName(databaseDriver); 
        // Step 2: Creating Connection
        connection = DriverManager.getConnection(HOSTINFO, "N00469373", "oracle");
        System.out.println("Database connection established: " + HOSTINFO);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
    }
    
    public void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Database connection closed."); 
    }

    public static boolean readUser(String username) {
        try {
            String sql = "SELECT username, password FROM USERS WHERE USERNAME = ?";
            PreparedStatement checkUser = connection.prepareStatement(sql);
            checkUser.setString(1, username); 
            userSet = checkUser.executeQuery();
            if (userSet.next()) {
                return true; 
            }
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error searching for user in database. Program terminated.", "Error Searching User Data", JOptionPane.ERROR_MESSAGE);
            System.exit(1); 
        } 
        return false; 
     } 
    
    public static boolean readUser(String username, String password) {
        try {
            String sql = "SELECT username, password FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement checkUser = connection.prepareStatement(sql);
            checkUser.setString(1, username); 
            checkUser.setString(2, password); 
            userSet = checkUser.executeQuery();
            if (userSet.next()) {
                return true; 
            }
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error searching for user in database. Program terminated.", "Error Searching User Data", JOptionPane.ERROR_MESSAGE);
            System.exit(1); 
        } 
        return false; 
     } 
    
    public static void writeUser(String username, String password) {
        try {
            String sql = "INSERT INTO USERS VALUES" + "(?,?)";
            PreparedStatement insertUser = connection.prepareStatement(sql);
            insertUser.setString(1, username);
            insertUser.setString(2, password);
            insertUser.executeUpdate();
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error writing user in database. Program terminated.", "Error Writing User Data", JOptionPane.ERROR_MESSAGE);
            System.exit(1); 
        } 
    }
    
    public static void readIncome() {
        String userID = UserStatus.getCurrentUser();
        IncomeStatus userIncome = IncomeStatus.getInstance(); 
        double salary, investments, tax, bonus, total;
        try { 
            String sqlStm = "SELECT salary, investments, bonus, taxcredits FROM INCOMEVALUES WHERE username = ?"; 
            PreparedStatement incomeStatement = connection.prepareStatement(sqlStm);
            incomeStatement.setString(1, userID);
            incomeSet = incomeStatement.executeQuery(); 
            if (incomeSet.isBeforeFirst()) {
                incomeSet.next(); 
                salary = incomeSet.getDouble(1);
                investments = incomeSet.getDouble(2); 
                tax = incomeSet.getDouble(3); 
                bonus = incomeSet.getDouble(4); 
                total = salary + investments + tax + bonus; 
                userIncome.setSalary(salary);
                userIncome.setInvestments(investments);
                userIncome.setTax(tax); 
                userIncome.setBonus(bonus);
                userIncome.setTotal(total); 
            } 
            else {
                String createDBEntry = "INSERT INTO INCOMEVALUES VALUES" + "(?,0,0,0,0)";
                PreparedStatement createDBEntryStm = connection.prepareStatement(createDBEntry);
                createDBEntryStm.setString(1, userID);
                createDBEntryStm.executeUpdate(); 
                userIncome.setSalary(0.0);
                userIncome.setInvestments(0.0);
                userIncome.setTax(0.0); 
                userIncome.setBonus(0.0);
                userIncome.setTotal(0.0); 
            } 
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error loading income from database. Program terminated.", "Error Loading Income", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }  
    }

    public static void updateIncome(String userId, double salary, double investments, double tax, double bonus, double total) {
        try {
            String updateDBEntry = "UPDATE INCOMEVALUES SET salary = ?, investments = ?,bonus = ?, taxcredits = ? WHERE username = ?"; 
            PreparedStatement updateDBEntryStm = connection.prepareStatement(updateDBEntry);
            updateDBEntryStm.setDouble(1, salary);
            updateDBEntryStm.setDouble(2, investments);
            updateDBEntryStm.setDouble(3, bonus);
            updateDBEntryStm.setDouble(4, tax);
            updateDBEntryStm.setString(5, userId);
            updateDBEntryStm.executeUpdate(); 
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Failed to update database entry. Program terminated.", "Error Loading Income", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }       
    }
    
    public static void readBudget() {
        String userID = UserStatus.getCurrentUser();
        BudgetStatus userBudget = BudgetStatus.getInstance(); 
        double rent, groceries, clothing, transportation, education, entertainment, savings, other; 
        try { 
            String sqlStm = "SELECT rent, groceries, clothing, transportation, education, entertainment, savings, other FROM BUDGETPERCENTAGES WHERE username = ?"; 
            PreparedStatement budgetStatement = connection.prepareStatement(sqlStm);
            budgetStatement.setString(1, userID);
            budgetSet = budgetStatement.executeQuery(); 
            if (budgetSet.isBeforeFirst()) {
                budgetSet.next(); 
                rent = budgetSet.getDouble(1);
                groceries = budgetSet.getDouble(2); 
                clothing = budgetSet.getDouble(3); 
                transportation = budgetSet.getDouble(4); 
                education = budgetSet.getDouble(5); 
                entertainment = budgetSet.getDouble(6); 
                savings = budgetSet.getDouble(7); 
                other = budgetSet.getDouble(8); 
                userBudget.setRentPercent(rent);
                userBudget.setGroceryPercent(groceries);
                userBudget.setClothingPercent(clothing);
                userBudget.setTransportationPercent(transportation);
                userBudget.setEducationPercent(education);
                userBudget.setEntertainmentPercent(entertainment);
                userBudget.setSavingsPercent(savings);
                userBudget.setOtherPercent(other);
            } 
            else {
                String createDBEntry = "INSERT INTO BUDGETPERCENTAGES VALUES" + "(?,0,0,0,0,0,0,0,0)";
                PreparedStatement createDBEntryStm = connection.prepareStatement(createDBEntry);
                createDBEntryStm.setString(1, userID);
                createDBEntryStm.executeUpdate(); 
                userBudget.setRentPercent(0);
                userBudget.setGroceryPercent(0);
                userBudget.setClothingPercent(0);
                userBudget.setTransportationPercent(0);
                userBudget.setEducationPercent(0);
                userBudget.setEntertainmentPercent(0);
                userBudget.setSavingsPercent(0);
                userBudget.setOtherPercent(0);
            } 
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error loading budget from database. Program terminated.", "Error Loading Budget", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }  
    }

    public static void updateBudget(String userId, double rent, double groceries, double clothing, double transportation, double education, double entertainment, double savings, double other) {
        try {
            String updateDBEntry = "UPDATE BUDGETPERCENTAGES SET rent = ?, groceries = ?, clothing = ?, transportation = ?, education = ?, entertainment = ?, savings = ?, other = ? WHERE username = ?"; 
            PreparedStatement updateDBEntryStm = connection.prepareStatement(updateDBEntry);
            updateDBEntryStm.setDouble(1, rent);
            updateDBEntryStm.setDouble(2, groceries);
            updateDBEntryStm.setDouble(3, clothing);
            updateDBEntryStm.setDouble(4, transportation);
            updateDBEntryStm.setDouble(5, education);
            updateDBEntryStm.setDouble(5, education);
            updateDBEntryStm.setDouble(6, entertainment);
            updateDBEntryStm.setDouble(7, savings);
            updateDBEntryStm.setDouble(8, other);
            updateDBEntryStm.setString(9, userId); 
            updateDBEntryStm.executeUpdate(); 
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Failed to update database entry. Program terminated.", "Error Loading Budget", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }       
    }
    
    public static void writeExpense(String dateOfPurchase, String category, String description, double value) {
        try {
            int expenseID = 0;
            String sql = "INSERT INTO EXPENSES VALUES" + "(?,?,?,?,?)";
            PreparedStatement insertExepnse= connection.prepareStatement(sql);
            insertExepnse.setInt(1, expenseID);
            insertExepnse.setString(2, dateOfPurchase);
            insertExepnse.setString(3, category);
            insertExepnse.setString(4, description);
            insertExepnse.setDouble(5, value);
            insertExepnse.executeUpdate();
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error writing expense in database. Program terminated.", "Error Writing Expense Data", JOptionPane.ERROR_MESSAGE);
            System.exit(1); 
        } 
    }
    
    public static void writeExpenseUser(int expenseID, String userName ) {
        try {
            String sql = "INSERT INTO EXPENSEUSERS VALUES" + "(?,?)";
            PreparedStatement insertExepnseUser= connection.prepareStatement(sql);
            insertExepnseUser.setInt(1, expenseID);
            insertExepnseUser.setString(2, userName);
            insertExepnseUser.executeUpdate();
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error writing expense user in database. Program terminated.", "Error Writing Expense User Data", JOptionPane.ERROR_MESSAGE);
            System.exit(1); 
        } 
    }
    
    public static void updateExpense(int expenseID, String dateOfPurchase, String category, String description, double value) {
        try {
            String sql = "UPDATE EXPENSES SET dateOfPurchase = ?, category = ?, description = ?, value = ? WHERE expenseid = ?"; 
            PreparedStatement updateExpense = connection.prepareStatement(sql);
            updateExpense.setString(1, dateOfPurchase);
            updateExpense.setString(2, category);
            updateExpense.setString(3, description);
            updateExpense.setDouble(4, value);
            updateExpense.setInt(5, expenseID); 
            updateExpense.executeUpdate(); 
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Failed to update database entry. Program terminated.", "Error Loading Expense", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }       
    }
    
    public static void deleteExpense(int expenseID){
        try{
            String sql = "DELETE FROM EXPENSES WHERE expenseid = ?";
            PreparedStatement deleteExpense = connection.prepareStatement(sql);
            deleteExpense.setInt(1, expenseID);           
            deleteExpense.executeUpdate();
        }catch(SQLException s){
            JOptionPane.showMessageDialog(null, "Failed to delete database entry. Program terminated.", "Error Deleting Expense", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }
    }
    
    public static void deleteExpenseUser(int expenseID, String userName){
        try{
            String sql = "DELETE FROM EXPENSEUSERS WHERE expenseid = ? and username = ?";
            PreparedStatement deleteExpenseUser = connection.prepareStatement(sql);
            deleteExpenseUser.setInt(1, expenseID);
            deleteExpenseUser.setString(2, userName);
            deleteExpenseUser.executeUpdate();
        }catch(SQLException s){
            JOptionPane.showMessageDialog(null, "Failed to delete database entry. Program terminated.", "Error Deleting Expense User", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }
    }
}

