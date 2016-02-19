/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class IncomeStatus extends java.util.Observable {
        private static ArrayList<IncomeEntry> IncomeEntries = new ArrayList<IncomeEntry>(); 

    public static void addIncomeEntry(String userid, double salary, double investments, double bonus, double tax) {
        IncomeEntries.add(new IncomeEntry(userid,salary,investments,bonus,tax)); 
    }
    
    public static void addIncomeEntry(double salary, double investments, double bonus, double tax) {
        IncomeEntries.add(new IncomeEntry(UserStatus.getCurrentUser(),salary,investments,bonus,tax)); 
        FileIO.writeIncome(UserStatus.getCurrentUser(), salary, investments, tax, bonus, tax);
    }
    
    public double getSalary(int userIndex) {
        return IncomeEntries.get(userIndex).getSalary();
    }
    
    public double getInvestments(int userIndex) {
        return IncomeEntries.get(userIndex).getInvestments();
    }
    
    public double getBonus(int userIndex) {
        return IncomeEntries.get(userIndex).getBonus();
    }
    
    public double getTax(int userIndex) {
        return IncomeEntries.get(userIndex).getTax();
    }
    
    public double getTotal(int userIndex) {
        if (userIndex >= 0) {
            return IncomeEntries.get(userIndex).getTotal();
        }
        return 0; 
    }
    
    public void updateIncomeEntries() {
        IncomeEntries = FileIO.readIncome(); 
    }
    
    public String getIncomeEntryString() {
        String incomeEntryString = ""; 
        int userIndex = searchIncomeEntry(); 
        if (userIndex == -1) {
            incomeEntryString = String.format("MONTHLY INCOME\r\n\r\nNo income information saved.\r\n\r\n", UserStatus.getCurrentUser()); 
        }
        else {
            
            String overviewFormat = "INCOME INFORMATION\r\n\r\n%1$-20s    %2$24s    %3$-17s\r\n\r\n";
            incomeEntryString += String.format(overviewFormat, "Category", "Percentage of Income", "Value"); 
            overviewFormat = "%1$-20s    %2$23.2f%%    $%3$-15.2f\r\n";
            incomeEntryString += String.format(overviewFormat, "Salary", (getSalary(userIndex) / getTotal(userIndex)) * 100 , getSalary(userIndex));
            incomeEntryString += String.format(overviewFormat, "Investments", (getInvestments(userIndex) / getTotal(userIndex)) * 100 , getInvestments(userIndex));
            incomeEntryString += String.format(overviewFormat, "Bonus", (getBonus(userIndex) / getTotal(userIndex)) * 100 , getBonus(userIndex));
            incomeEntryString += String.format(overviewFormat, "Grants/Tax Credits", (getTax(userIndex) / getTotal(userIndex)) * 100 , getTax(userIndex));
            incomeEntryString += String.format(overviewFormat, "Total Income", 100.0, getTotal(userIndex));
            incomeEntryString += String.format("\r\n\r\n"); 

            
            //incomeEntryString = String.format("MONTHLY INCOME\r\n\r\nSalary: $%.2f\r\nInvestments: $%.2f\r\nBonus: $%.2f\r\nGrants/Tax Credits: $%.2f\r\nTotal Income: $%.2f\r\n\r\n",
            //getSalary(userIndex), getInvestments(userIndex), getBonus(userIndex), getTax(userIndex), getTotal(userIndex)); 
        } 
        return incomeEntryString; 
    }
    
    public double[] getIncomeData() {
        int userIndex = searchIncomeEntry();
        double[] incomeData = new double[4]; 
        if (userIndex == -1) {
            incomeData[0] = 0;
            incomeData[1] = 0;
            incomeData[2] = 0;
            incomeData[3] = 0; 
        }
        else {
            incomeData[0] = (getSalary(userIndex) / getTotal(userIndex)) * 100; 
            incomeData[1] = (getInvestments(userIndex) / getTotal(userIndex)) * 100;
            incomeData[2] = (getBonus(userIndex) / getTotal(userIndex)) * 100;
            incomeData[3] = (getTax(userIndex) / getTotal(userIndex)) * 100; 
        }
        
        // Removing 0 values from array for graph display
        int length = 0;
        for (int i=0; i<incomeData.length; i++){
            if (incomeData[i] > 0)
                length++;
        }
        double[] newIncomeData = new double[length];
        for (int i=0, j=0; i<incomeData.length; i++){
            if (incomeData[i] > 0) {
                newIncomeData[j] = incomeData[i];
                j++;
            }
        }
        
        return newIncomeData; 
    }
    
    public String[] getIncomeLabels() {
        int userIndex = searchIncomeEntry();
        double[] incomeData = new double[4];

        if (userIndex == -1) {
            incomeData[0] = 0;
            incomeData[1] = 0;
            incomeData[2] = 0;
            incomeData[3] = 0; 
        }
        else {
            incomeData[0] = (getSalary(userIndex) / getTotal(userIndex)) * 100; 
            incomeData[1] = (getInvestments(userIndex) / getTotal(userIndex)) * 100;
            incomeData[2] = (getBonus(userIndex) / getTotal(userIndex)) * 100;
            incomeData[3] = (getTax(userIndex) / getTotal(userIndex)) * 100; 
        }
        int length = 0;
        for (int i=0; i<incomeData.length; i++){
            if (incomeData[i] > 0)
                length++;
        }

        String[] incomeLabels = new String[length];
        int count = 0; 
        if (incomeData[0] > 0) {
            incomeLabels[count] = "Salary";
            count++;
        }
        if (incomeData[1] > 0) {
            incomeLabels[count] = "Investments";
            count++;
        }
        if (incomeData[2] > 0) {
            incomeLabels[count] = "Bonus";
            count++;
        }
        if (incomeData[3] > 0) {
            incomeLabels[count] = "Grants/Tax Credits"; 
        }
        
        return incomeLabels;
    }
        

    public int searchIncomeEntry() {
            IncomeEntries = FileIO.readIncome(); 
            String userid = UserStatus.getCurrentUser();
            int userIndex = -1;
            if (IncomeEntries != null) {
                for (int i = IncomeEntries.size() - 1; i >= 0; i--) {
                        if  (IncomeEntries.get(i).getUserid().equals(userid)) {
                            userIndex = i;
                            return userIndex; 
                        }
                    }
            }
            return -1; 
    }
    
    public void updateIncomeEntry(int userIndex, double salary, double investments, double tax, double bonus, double total) {
        IncomeEntries.get(userIndex).setSalary(salary);
        IncomeEntries.get(userIndex).setInvestments(investments);
        IncomeEntries.get(userIndex).setTax(tax);
        IncomeEntries.get(userIndex).setBonus(bonus);
        IncomeEntries.get(userIndex).setTotal(total);
        FileIO.writeIncome(UserStatus.getCurrentUser(), salary, investments, tax, bonus, total);
    }
    
    
    
}

