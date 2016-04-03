
package model;

public class IncomeStatus {
    private static int totalIncomeEntries;
    private int incomeEntryNumber; 
    private String userid; 
    private static double salary;
    private static double investments;
    private static double bonus;
    private static double tax;
    private static double total;
    private static IncomeStatus instance; 
    
    // Stores user's income data 
    
    private IncomeStatus() {}; 
    
    public static IncomeStatus getInstance() {
        if (instance == null) {
            instance = new IncomeStatus(); 
        }
        return instance; 
    } 
    
        
    public void retrieveValues() {
        DBAccess.readIncome();
    }
     
    public static void updateIncomeEntry(double salary, double investments, double tax, double bonus, double total) {
        IncomeStatus.setSalary(salary);
        IncomeStatus.setInvestments(investments);
        IncomeStatus.setTax(tax);
        IncomeStatus.setBonus(bonus);
        IncomeStatus.setTotal(total);
        DBAccess.updateIncome(UserStatus.getCurrentUser(), salary, investments, tax, bonus, total);
    }
    
    
    public String getIncomeEntryString() { 
        DBAccess.readIncome();
        String overviewFormat = "INCOME INFORMATION\r\n\r\n%1$-20s    %2$24s    %3$-17s\r\n\r\n";
        String incomeEntryString = String.format(overviewFormat, "Category", "Percentage of Income", "Value"); 
        overviewFormat = "%1$-20s    %2$23.2f%%    $%3$-15.2f\r\n";
        
        // fixing percentage result if 0/0 to display 0 instead of NaN
        double salaryPercent, investmentsPercent, bonusPercent, taxPercent; 
        if (Double.isNaN((this.getSalary() / this.getTotal()) * 100)) {
            salaryPercent = 0; 
        }
        else {
            salaryPercent = (this.getSalary() / this.getTotal()) * 100; 
        }
        if (Double.isNaN((this.getInvestments() / this.getTotal()) * 100)) {
            investmentsPercent = 0; 
        }
        else {
            investmentsPercent = (this.getInvestments() / this.getTotal()) * 100; 
        }
        if (Double.isNaN((this.getBonus() / this.getTotal()) * 100)) {
            bonusPercent = 0; 
        }
        else {
            bonusPercent = (this.getBonus() / this.getTotal()) * 100; 
        }
        if (Double.isNaN((this.getTax() / this.getTotal()) * 100)) {
            taxPercent = 0; 
        }
        else {
            taxPercent = (this.getTax() / this.getTotal()) * 100; 
        }
        
        
        incomeEntryString += String.format(overviewFormat, "Salary", salaryPercent, this.getSalary());
        incomeEntryString += String.format(overviewFormat, "Investments", investmentsPercent, this.getInvestments());
        incomeEntryString += String.format(overviewFormat, "Bonus", bonusPercent, this.getBonus());
        incomeEntryString += String.format(overviewFormat, "Grants/Tax Credits", taxPercent, this.getTax());
        incomeEntryString += String.format(overviewFormat, "Total Income", 100.0, this.getTotal());
        incomeEntryString += String.format("\r\n\r\n"); 
        return incomeEntryString; 
    }
    
    public double[] getIncomeData() {
        DBAccess.readIncome();
        double[] incomeData = new double[4]; 
        incomeData[0] = (this.getSalary() / this.getTotal()) * 100; 
        incomeData[1] = (this.getInvestments() / this.getTotal()) * 100;
        incomeData[2] = (this.getBonus() / this.getTotal()) * 100;
        incomeData[3] = (this.getTax() / this.getTotal()) * 100; 
        
        
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
        DBAccess.readIncome(); 
        double[] incomeData = new double[4];
            incomeData[0] = (this.getSalary() / this.getTotal()) * 100; 
            incomeData[1] = (this.getInvestments() / this.getTotal()) * 100;
            incomeData[2] = (this.getBonus() / this.getTotal()) * 100;
            incomeData[3] = (this.getTax() / this.getTotal()) * 100; 
        
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
        

    
    public static double getSalary() {
        return salary;
    }
    
    public static void setSalary(double salary) {
        IncomeStatus.salary = salary;
    }
    
    public static double getInvestments() {
        return investments;
    }
    
    public static void setInvestments(double investments) {
        IncomeStatus.investments = investments;
    }
    
    public static double getBonus() {
        return bonus;
    }
    
    public static void setBonus(double bonus) {
        IncomeStatus.bonus = bonus;
    }
   
    
    public static double getTax() {
        return tax;
    }
    
    public static void setTax(double tax) {
        IncomeStatus.tax = tax;
    }
    
    public static double getTotal() {
        return total;
    }
    
    public static void setTotal(double total) {
        IncomeStatus.total = total;
    }
    
    public String getUserid() {
        return userid;
    }
    
}
