
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ExpenseStatus {
    private static ArrayList<ExpenseEntry> ExpenseEntries = new ArrayList<ExpenseEntry>(); 
        
         
    public static void addExpenseEntry(String userid, int day, int month, int year, String description, String category, double value) {
        ExpenseEntries.add(new ExpenseEntry(userid,day, month, year, description, category, value)); 
    }
    
    public static void addExpenseEntry(int day, int month, int year, String description, String category, double value) {
        ExpenseEntries.add(new ExpenseEntry(UserStatus.getCurrentUser(),day, month, year, description, category, value)); 
        FileIO.writeExpense(UserStatus.getCurrentUser(), ExpenseEntry.getTotalExpenseEntries(), day, month, year, description, category, value); 
    }
    
     public static void updateExpenseEntry(ExpenseEntry initExpenseEntry,ExpenseEntry updatedExpenseEntry){
        FileIO.deleteExpense(initExpenseEntry.getUserid(),initExpenseEntry.getExpenseID(),initExpenseEntry.getDay(), initExpenseEntry.getMonth(),
                initExpenseEntry.getYear(),initExpenseEntry.getDescription(),initExpenseEntry.getCategory(),initExpenseEntry.getValue());
        FileIO.writeExpense(updatedExpenseEntry.getUserid(),updatedExpenseEntry.getExpenseID(),updatedExpenseEntry.getDay(), updatedExpenseEntry.getMonth(),
                updatedExpenseEntry.getYear(),updatedExpenseEntry.getDescription(),updatedExpenseEntry.getCategory(),updatedExpenseEntry.getValue());
    }
    
    public static void deleteExpenseEntry(String userId, int expenseId, int day, int month, int year, String description, String category, double value){
        FileIO.deleteExpense(userId, expenseId, day, month, year, description, category, value);
    }
       
    
    
    
    public static void setExpenses() {
        ExpenseEntries = FileIO.readExpenses(); 
    }
    
    public static ArrayList<ExpenseEntry> filterExpenses(String category,String month){
        setExpenses(); 
        ArrayList<ExpenseEntry> allExpenses = ExpenseEntries; 
        ArrayList<ExpenseEntry> filteredExpenses = new ArrayList<ExpenseEntry>();
        if(category.equals("View All")&&month.equals("View All"))
        {
            filteredExpenses = allExpenses;            
        }
        else if(category.equals("View All"))
        {
            for(int i=0;i<allExpenses.size();i++)
            {
                if(allExpenses.get(i).getMonthString().equals(month))
                    filteredExpenses.add(allExpenses.get(i));
            }           
        }
        else if(month.equals("View All"))
        {
            for(int i=0;i<allExpenses.size();i++)
                if(allExpenses.get(i).getCategory().equals(category))
                    filteredExpenses.add(allExpenses.get(i));            
        }
        else
        {
            for(int i=0;i<allExpenses.size();i++)
                if(allExpenses.get(i).getCategory().equals(category)&&allExpenses.get(i).getMonthString().equals(month))
                    filteredExpenses.add(allExpenses.get(i));           
        }        
        Comparator<ExpenseEntry> comparator = Comparator.comparingInt(ExpenseEntry::getYear).thenComparingInt(ExpenseEntry::getMonth)
                .thenComparingInt(ExpenseEntry::getDay).thenComparing(ExpenseEntry::getCategory).thenComparingDouble(ExpenseEntry::getValue);
        Collections.sort(filteredExpenses, comparator); 
        return filteredExpenses;        
    }
    
    public static String getExpensesString(String category, String month) {
        ArrayList<ExpenseEntry> allExpenses = filterExpenses(category, month); 
        double[] expenseTotals = getExpenseTotals(allExpenses); 
        String expenseString = "";
        if (allExpenses.size() >= 1) {
            String overviewFormat = "EXPENSES OVERVIEW\r\n\r\n%1$-20s    %2$24s    %3$-17s\r\n\r\n";
            expenseString += String.format(overviewFormat, "Category", "Percentage of Expenses", "Total Expenses"); 
            overviewFormat = "%1$-20s    %2$23.2f%%    $%3$-15.2f\r\n";
            expenseString += String.format(overviewFormat, "Rent/Utilities", (expenseTotals[0] / expenseTotals[7]) * 100, expenseTotals[0]); 
            expenseString += String.format(overviewFormat, "Groceries", (expenseTotals[1] / expenseTotals[7]) * 100,  expenseTotals[1]); 
            expenseString += String.format(overviewFormat, "Clothing",  (expenseTotals[2] / expenseTotals[7]) * 100,  expenseTotals[2]) ; 
            expenseString += String.format(overviewFormat, "Transportation/Car",  (expenseTotals[3] / expenseTotals[7] * 100), expenseTotals[3]); 
            expenseString += String.format(overviewFormat, "Education", ( expenseTotals[4] / expenseTotals[7]) * 100,  expenseTotals[4]); 
            expenseString += String.format(overviewFormat, "Entertainment", (expenseTotals[5] / expenseTotals[7]) * 100,  expenseTotals[5]); 
            expenseString += String.format(overviewFormat, "Other",  (expenseTotals[6] / expenseTotals[7]) * 100, expenseTotals[6]); 
            expenseString += String.format(overviewFormat, "Total Expenses", 100.0, expenseTotals[7]); 
            expenseString += "\r\n\r\n"; 
            String format = "EXPENSES LOG\r\n\r\n%1$-10s    %2$-18s    %3$-20s    %4$-10s\r\n\r\n";
            expenseString += String.format(format, "Date", " Category", " Description", " Value");
            format = "%1$-10s    %2$-18s    %3$-20s    $%4$-15.2f\r\n";
            for (int i = 0; i < allExpenses.size(); i++) {
                expenseString += String.format(format, allExpenses.get(i).getDate(), allExpenses.get(i).getCategory(), allExpenses.get(i).getDescription(), allExpenses.get(i).getValue() ); 
            }
        }
        else {
             expenseString = String.format("EXPENSES INFORMATION\r\n\r\nNo expense information saved.\r\n\r\n"); 
        }
            return expenseString;

    }    
    public static Object[][] expenseTableFormat(ArrayList<ExpenseEntry> expenseData) {
        Object[][] rowData = new Object[expenseData.size()][6];
        for(int i=0;i<expenseData.size();i++) {
            rowData[i][0] = expenseData.get(i).getUserid();
            rowData[i][1] = expenseData.get(i).getExpenseID();
            rowData[i][2] = expenseData.get(i).getDate();
            rowData[i][3] = expenseData.get(i).getCategory();
            rowData[i][4] = expenseData.get(i).getDescription();
            rowData[i][5] = expenseData.get(i).getValue()+"";
        }
        return rowData;         
    }
    
    
    
    public static double[] getExpenseTotals(ArrayList<ExpenseEntry> filteredExpenses) {
        double rent = 0;
        double groceries = 0;
        double clothing = 0;
        double transportation = 0;
        double education = 0;
        double entertainment = 0;
        double other = 0;
        double total = 0;

        for (int i = 0; i < filteredExpenses.size(); i++) {
            if (filteredExpenses.get(i).getCategory().equals("Rent/Utilities")) {
                rent += filteredExpenses.get(i).getValue(); 
            }
            else if (filteredExpenses.get(i).getCategory().equals("Groceries")) {
                groceries += filteredExpenses.get(i).getValue(); 
            }
            else if (filteredExpenses.get(i).getCategory().equals("Clothing")) {
                clothing += filteredExpenses.get(i).getValue(); 
            }
            else if (filteredExpenses.get(i).getCategory().equals("Transportation/Car")) {
                transportation += filteredExpenses.get(i).getValue(); 
            }
            else if (filteredExpenses.get(i).getCategory().equals("Education")) {
                education += filteredExpenses.get(i).getValue(); 
            }
            else if (filteredExpenses.get(i).getCategory().equals("Entertainment")) {
                entertainment += filteredExpenses.get(i).getValue(); 
            }
            else if (filteredExpenses.get(i).getCategory().equals("Other")) {
                other += filteredExpenses.get(i).getValue(); 
            }
            
            total += filteredExpenses.get(i).getValue(); 

        }
        
        double[] expenseTotals = {rent, groceries, clothing, transportation, education, entertainment, other, total}; 
        return expenseTotals;
    }
    
        // formatting expense data for pie charts
    public static double[] getExpenseData(ArrayList<ExpenseEntry> filteredExpenses) {
        double[] expenseTotals = getExpenseTotals(filteredExpenses);  
        expenseTotals[0] = (expenseTotals[0]/ expenseTotals[7]) * 100; 
        expenseTotals[1] = (expenseTotals[1]/ expenseTotals[7]) * 100; 
        expenseTotals[2] = (expenseTotals[2]/ expenseTotals[7]) * 100; 
        expenseTotals[3] = (expenseTotals[3]/ expenseTotals[7]) * 100; 
        expenseTotals[4] = (expenseTotals[4]/ expenseTotals[7]) * 100; 
        expenseTotals[5] = (expenseTotals[5]/ expenseTotals[7]) * 100; 
        expenseTotals[6] = (expenseTotals[6]/ expenseTotals[7]) * 100; 
        expenseTotals[7] = 0; 

        // Removing 0 values from array for graph display
        int length = 0;
        for (int i=0; i<expenseTotals.length; i++){
            if (expenseTotals[i] > 0)
                length++;
        }
        double[] newExpenseData = new double[length];
        for (int i=0, j=0; i<expenseTotals.length; i++){
            if (expenseTotals[i] > 0) {
                newExpenseData[j] = expenseTotals[i];
                j++;
            }
        }
        
        return newExpenseData; 
        
    }
    
    public static String[] getExpenseLabels(ArrayList<ExpenseEntry> filteredExpenses) {
        double[] expenseTotals = getExpenseTotals(filteredExpenses);  
        expenseTotals[0] = (expenseTotals[0]/ expenseTotals[7]) * 100; 
        expenseTotals[1] = (expenseTotals[1]/ expenseTotals[7]) * 100; 
        expenseTotals[2] = (expenseTotals[2]/ expenseTotals[7]) * 100; 
        expenseTotals[3] = (expenseTotals[3]/ expenseTotals[7]) * 100; 
        expenseTotals[4] = (expenseTotals[4]/ expenseTotals[7]) * 100; 
        expenseTotals[5] = (expenseTotals[5]/ expenseTotals[7]) * 100; 
        expenseTotals[6] = (expenseTotals[6]/ expenseTotals[7]) * 100; 
        expenseTotals[7] = 0; 
        
        // Removing 0 values from array for graph display
        int length = 0;
        for (int i=0; i<expenseTotals.length; i++){
            if (expenseTotals[i] > 0)
                length++;
        }
        
        String[] expenseLabels = new String[length];
        int count = 0; 
        if (expenseTotals[0] > 0) {
            expenseLabels[count] = "Rent/Utilities";
            count++;
        }
        if (expenseTotals[1] > 0) {
            expenseLabels[count] = "Groceries";
            count++;
        }
        if (expenseTotals[2] > 0) {
            expenseLabels[count] = "Clothing";
            count++;
        }
        if (expenseTotals[3] > 0) {
            expenseLabels[count] = "Transportation/Car"; 
            count++;
        }
        if (expenseTotals[4] > 0) {
            expenseLabels[count] = "Education";
            count++;
        }
        if (expenseTotals[5] > 0) {
            expenseLabels[count] = "Entertainment"; 
            count++;
        }
        if (expenseTotals[6] > 0) {
            expenseLabels[count] = "Other"; 
            count++; 
        }

        return expenseLabels;

    }
    
    
}

