
package model;

public class IncomeEntry {
    private static int totalIncomeEntries;
    private int incomeEntryNumber; 
    private String userid; 
    private double salary;
    private double investments;
    private double bonus;
    private double tax;
    private double total;
    
    // Store's each users income data 
    
    public IncomeEntry(String userid, double salary, double investments, double bonus, double tax) {
        totalIncomeEntries++; 
        this.incomeEntryNumber = totalIncomeEntries;
        this.userid = userid; 
        this.salary = salary;
        this.investments = investments;
        this.bonus = bonus;
        this.tax = tax;
        this.total = salary + investments + bonus + tax; 
    }
    

    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public double getInvestments() {
        return investments;
    }
    
    public void setInvestments(double investments) {
        this.investments = investments;
    }
    
    public double getBonus() {
        return bonus;
    }
    
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
   
    
    public double getTax() {
        return tax;
    }
    
    public void setTax(double tax) {
        this.tax = tax;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getUserid() {
        return userid;
    }
    
}
