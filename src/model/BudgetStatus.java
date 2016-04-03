/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Admin
 */
public class BudgetStatus {
    private static BudgetStatus instance;
    private String userid; 
    private double rentPercent;
    private double groceryPercent;
    private double clothingPercent;
    private double transportationPercent;
    private double educationPercent;
    private double savingsPercent;
    private double entertainmentPercent;
    private double otherPercent;
    
    public void setBudgetStatus(String userid, double rentPercent, double groceryPercent, double clothingPercent, double transportationPercent, double educationPercent, double savingsPercent, double entertainmentPercent, double otherPercent) {
        this.userid = userid; 
        this.rentPercent = rentPercent;
        this.groceryPercent = groceryPercent;
        this.clothingPercent = clothingPercent;
        this.transportationPercent = transportationPercent;
        this.educationPercent = educationPercent;
        this.savingsPercent = savingsPercent; 
        this.entertainmentPercent = entertainmentPercent; 
        this.otherPercent = otherPercent; 
    }

    public double getRentPercent() {
        return rentPercent;
    }
    
    public double getGroceryPercent() {
        return groceryPercent;
    }
    
    public double getClothingPercent() {
        return clothingPercent;
    }
    
    public double getTransportationPercent() {
        return transportationPercent;
    }
    
    public double getEducationPercent() {
        return educationPercent;
    }
    
    public double getSavingsPercent() {
        return savingsPercent;
    }
    
    public double getEntertainmentPercent() {
        return entertainmentPercent;
    }
    
    public double getOtherPercent() {
        return otherPercent;
    }

    public String getUserid() {
        return userid;
    }
    
    public void setRentPercent(double rentPercent) {
        this.rentPercent =  rentPercent; 
    }
    
    public void setGroceryPercent(double groceryPercent) {
        this.groceryPercent =  groceryPercent;
    }
    
    public void setClothingPercent(double clothingPercent) {
        this.clothingPercent =  clothingPercent;
    }
    
    public void setTransportationPercent(double transportationPercent) {
        this.transportationPercent =  transportationPercent;
    }
    
    public void setEducationPercent(double educationPercent) {
        this.educationPercent =  educationPercent;
    }
    
    public void setSavingsPercent(double savingsPercent) {
        this.savingsPercent =  savingsPercent;
    }
    
    public void setEntertainmentPercent(double entertainmentPercent) {
        this.entertainmentPercent =  entertainmentPercent;
    }
    
    public void setOtherPercent(double otherPercent) {
        this.otherPercent =  otherPercent;
    }
    
    private BudgetStatus() {}; 
    
    public static BudgetStatus getInstance() {
        if (instance == null) {
            instance = new BudgetStatus(); 
        }
        return instance; 
    }
    
    public void loadBudget() {
        DBAccess.readBudget();
    }

    public void updateBudgetEntry(double rentPercent, double groceryPercent, double clothingPercent, double transportationPercent, double educationPercent,  double entertainmentPercent, double savingsPercent, double otherPercent)  {
        DBAccess.updateBudget(UserStatus.getCurrentUser(), rentPercent, groceryPercent, clothingPercent, transportationPercent, educationPercent, entertainmentPercent, savingsPercent, otherPercent);    
    }
    
    public String getBudgetEntryString() {
        DBAccess.readBudget();
        String budgetEntryString = ""; 
        IncomeStatus income = IncomeStatus.getInstance(); 
        double totalIncome = income.getTotal(); 
        
        String overviewFormat = "BUDGET INFORMATION\r\n\r\n%1$-20s    %2$24s    %3$-17s\r\n\r\n";
        budgetEntryString += String.format(overviewFormat, "Category", "Percentage of Income", "Value"); 
        overviewFormat = "%1$-20s    %2$23.2f%%    $%3$-15.2f\r\n";
        budgetEntryString += String.format(overviewFormat, "Rent/Utilities", getRentPercent(), (getRentPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Groceries", getGroceryPercent(), (getGroceryPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Clothing", getClothingPercent(), (getClothingPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Transportaton/Car", getTransportationPercent(), (getTransportationPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Education", getEducationPercent(), (getEducationPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Entertainment", getEntertainmentPercent(), (getEntertainmentPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Other", getOtherPercent(), (getOtherPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Savings", getSavingsPercent(), (getSavingsPercent() / 100) * totalIncome); 
        budgetEntryString += String.format(overviewFormat, "Total Budget", 100.00, totalIncome); 
        budgetEntryString += "\r\n\r\n";
        return budgetEntryString; 
    }
    
    public double[] getBudgetData() {
        DBAccess.readBudget();
        double[] budgetData = new double[8]; 
        budgetData[0] = getRentPercent(); 
        budgetData[1] = getGroceryPercent(); 
        budgetData[2] = getClothingPercent(); 
        budgetData[3] = getTransportationPercent(); 
        budgetData[4] = getEducationPercent(); 
        budgetData[5] = getEntertainmentPercent(); 
        budgetData[6] = getSavingsPercent(); 
        budgetData[7] = getOtherPercent(); 
        
        
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
        DBAccess.readBudget(); 
        double[] budgetData = new double[8]; 
        budgetData[0] = getRentPercent(); 
        budgetData[1] = getGroceryPercent(); 
        budgetData[2] = getClothingPercent(); 
        budgetData[3] = getTransportationPercent(); 
        budgetData[4] = getEducationPercent(); 
        budgetData[5] = getEntertainmentPercent(); 
        budgetData[6] = getSavingsPercent(); 
        budgetData[7] = getOtherPercent(); 
        
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
    
   
    
    

