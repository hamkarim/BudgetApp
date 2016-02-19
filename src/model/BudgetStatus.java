/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BudgetStatus {
    protected static ArrayList<BudgetEntry> BudgetEntries = new ArrayList<BudgetEntry>(); 

    public static void addBudgetEntry (String userid, double rentPercent, double groceryPercent, double clothingPercent, double transportationPercent, double educationPercent, double savingsPercent, double entertainmentPercent, double otherPercent) {
        BudgetEntries.add(new BudgetEntry(userid, rentPercent, groceryPercent, clothingPercent, transportationPercent, educationPercent, savingsPercent, entertainmentPercent, otherPercent)); 
    }
    
    public static void addBudgetEntry (double rentPercent, double groceryPercent, double clothingPercent, double transportationPercent, double educationPercent, double savingsPercent, double entertainmentPercent, double otherPercent) {
        BudgetEntries.add(new BudgetEntry(UserStatus.getCurrentUser(), rentPercent, groceryPercent, clothingPercent, transportationPercent, educationPercent, savingsPercent, entertainmentPercent, otherPercent)); 
        FileIO.writeBudget(UserStatus.getCurrentUser(), rentPercent, groceryPercent, clothingPercent, transportationPercent, educationPercent, entertainmentPercent, savingsPercent, otherPercent);
    }
        
    public void updateBudgetEntry(int userIndex, double rentPercent, double groceryPercent, double clothingPercent, double transportationPercent, double educationPercent, double savingsPercent, double entertainmentPercent, double otherPercent)  {
        BudgetEntries.get(userIndex).setRentPercent(rentPercent);
        BudgetEntries.get(userIndex).setGroceryPercent(groceryPercent);
        BudgetEntries.get(userIndex).setClothingPercent(clothingPercent);
        BudgetEntries.get(userIndex).setTransportationPercent(transportationPercent);
        BudgetEntries.get(userIndex).setEducationPercent(educationPercent);
        BudgetEntries.get(userIndex).setSavingsPercent(savingsPercent);
        BudgetEntries.get(userIndex).setEntertainmentPercent(entertainmentPercent);
        BudgetEntries.get(userIndex).setOtherPercent(otherPercent);
        FileIO.writeBudget(UserStatus.getCurrentUser(), rentPercent, groceryPercent, clothingPercent, transportationPercent, educationPercent, entertainmentPercent, savingsPercent, otherPercent);
    }
    
    public int searchBudgetEntry() {
            BudgetEntries = FileIO.readBudget(); 
            String userid = UserStatus.getCurrentUser();
            int userIndex = -1;
            for (int i = BudgetEntries.size() - 1; i >= 0; i--) {
                    if  (BudgetEntries.get(i).getUserid().equals(userid)) {
                        userIndex = i;
                        return userIndex; 
                    }
                }
            return -1; 
    }
    
    public String getBudgetEntryString() {
        String budgetEntryString = ""; 
        IncomeStatus income = new IncomeStatus();
        int incomeIndex = income.searchIncomeEntry(); 
        double totalIncome = income.getTotal(incomeIndex); 
        
        int userIndex = searchBudgetEntry(); 
        if (userIndex == -1) {
            budgetEntryString = String.format("BUDGET INFORMATION\r\n\r\nNo budget information saved.\r\n\r\n"); 
        }
        else {
            String overviewFormat = "BUDGET INFORMATION\r\n\r\n%1$-20s    %2$24s    %3$-17s\r\n\r\n";
            budgetEntryString += String.format(overviewFormat, "Category", "Percentage of Income", "Value"); 
            overviewFormat = "%1$-20s    %2$23.2f%%    $%3$-15.2f\r\n";
            budgetEntryString += String.format(overviewFormat, "Rent/Utilities", getRentPercent(userIndex), (getRentPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Groceries", getGroceryPercent(userIndex), (getGroceryPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Clothing", getClothingPercent(userIndex), (getClothingPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Transportaton/Car", getTransportationPercent(userIndex), (getTransportationPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Education", getEducationPercent(userIndex), (getEducationPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Entertainment", getEntertainmentPercent(userIndex), (getEntertainmentPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Other", getOtherPercent(userIndex), (getOtherPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Savings", getSavingsPercent(userIndex), (getSavingsPercent(userIndex) / 100) * totalIncome); 
            budgetEntryString += String.format(overviewFormat, "Total Budget", 100.00, totalIncome); 
            budgetEntryString += "\r\n\r\n";
        }
        return budgetEntryString; 
    }
    
    public double getEducationPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getEducationPercent(); 
    }
    
    public double getEntertainmentPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getEntertainmentPercent(); 
    }
    
    public double getSavingsPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getSavingsPercent(); 
    }
    
    public double getOtherPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getOtherPercent(); 
    }
            
    public double getRentPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getRentPercent(); 
    }
    public double getGroceryPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getGroceryPercent(); 
    }
    
    public double getClothingPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getClothingPercent(); 
    }
    
    public double getTransportationPercent(int userIndex) {
        return BudgetEntries.get(userIndex).getTransportationPercent(); 
    }
    
    public double[] getBudgetData() {
        int userIndex = searchBudgetEntry();
        double[] budgetData = new double[8]; 
        if (userIndex == -1) {
            budgetData[0] = 0;
            budgetData[1] = 0;
            budgetData[2] = 0;
            budgetData[3] = 0;
            budgetData[4] = 0;
            budgetData[5] = 0;
            budgetData[6] = 0;
            budgetData[7] = 0;
            
        }
        else {
            budgetData[0] = getRentPercent(userIndex); 
            budgetData[1] = getGroceryPercent(userIndex); 
            budgetData[2] = getClothingPercent(userIndex); 
            budgetData[3] = getTransportationPercent(userIndex); 
            budgetData[4] = getEducationPercent(userIndex); 
            budgetData[5] = getEntertainmentPercent(userIndex); 
            budgetData[6] = getSavingsPercent(userIndex); 
            budgetData[7] = getOtherPercent(userIndex); 
        }
        
        // Removing 0 values from array for graph display
        int length = 0;
        for (int i=0; i<budgetData.length; i++){
            if (budgetData[i] > 0)
                length++;
        }
        double[] newBudgetData = new double[length];
        for (int i=0, j=0; i<budgetData.length; i++){
            if (budgetData[i] > 0) {
                newBudgetData[j] = budgetData[i];
                j++;
            }
        }
        
        return newBudgetData; 
    }
    
    public String[] getBudgetLabels() {
        int userIndex = searchBudgetEntry();
        double[] budgetData = new double[8]; 
        if (userIndex == -1) {
            budgetData[0] = 0;
            budgetData[1] = 0;
            budgetData[2] = 0;
            budgetData[3] = 0;
            budgetData[4] = 0;
            budgetData[5] = 0;
            budgetData[6] = 0;
            budgetData[7] = 0;
            
        }
        else {
            budgetData[0] = getRentPercent(userIndex); 
            budgetData[1] = getGroceryPercent(userIndex); 
            budgetData[2] = getClothingPercent(userIndex); 
            budgetData[3] = getTransportationPercent(userIndex); 
            budgetData[4] = getEducationPercent(userIndex); 
            budgetData[5] = getEntertainmentPercent(userIndex); 
            budgetData[6] = getSavingsPercent(userIndex); 
            budgetData[7] = getOtherPercent(userIndex); 
        }
        
        // Removing 0 values from array for graph display
        int length = 0;
        for (int i=0; i<budgetData.length; i++){
            if (budgetData[i] > 0)
                length++;
        }
        
        
        String[] budgetLabels = new String[length];
        int count = 0; 
        if (budgetData[0] > 0) {
            budgetLabels[count] = "Rent/Utilities";
            count++;
        }
        if (budgetData[1] > 0) {
            budgetLabels[count] = "Groceries";
            count++;
        }
        if (budgetData[2] > 0) {
            budgetLabels[count] = "Clothing";
            count++;
        }
        if (budgetData[3] > 0) {
            budgetLabels[count] = "Transportation/Car"; 
            count++;
        }
        if (budgetData[4] > 0) {
            budgetLabels[count] = "Education"; 
            count++;
        }
        if (budgetData[5] > 0) {
            budgetLabels[count] = "Entertainment";
            count++;
        }
        if (budgetData[6] > 0) {
            budgetLabels[count] = "Savings"; 
            count++;
        }
        if (budgetData[7] > 0) {
            budgetLabels[count] = "Other"; 
            count++; 
        }
        
        return budgetLabels;
    }
}
    
   
    
    

