
package model;

public class ExpenseEntry {
    private String userName; 
    private int day;
    private int month;
    private int year;
    private String description;
    private String category;
    private double value;
    
    // Expense ID is auto generated and used to uniquely identify each expense entry, so that the user can delete expenses in the future
    private static int totalExpenseEntries; 
    private int expenseID;

    // Stores each user's expense data
    
    public ExpenseEntry(String userName, int expenseID, int day, int month, int year, String category, String description, double value) {
        this.expenseID = expenseID;
        this.userName = userName; 
        this.day = day;
        this.month = month;
        this.year = year; 
        this.description = description;
        this.category = category;
        this.value = value;
    }
            
    public int getExpenseID() {
        return expenseID;
    }
    
    public int getDay() {
        return day;
    }
    
    public int getMonth() {
        return month;
    }
    
    // Prints month as a 3 character string rather than numerically
    // Numeric variable is used primarily for sorting purposes while String representation is for display
    public String getMonthString() {
        String monthString = "";
        if (month == 1) {
            monthString = "Jan";
        }
        else if (month == 2) {
            monthString = "Feb";
        }
        else if (month == 3) {
            monthString = "Mar";
        }
        else if (month == 4) {
            monthString = "Apr";
        }
        else if (month == 5) {
            monthString = "May";
        }
        else if (month == 6) {
            monthString = "Jun";
        }
        else if (month == 7) {
            monthString = "Jul";
        }
        else if (month == 8) {
            monthString = "Aug";
        }
        else if (month == 10) {
            monthString = "Sep";
        }
        else if (month == 11) {
            monthString = "Nov";
        }
        else if (month == 12) {
            monthString = "Dec";
        }
        return monthString;
    }
        
    public int getYear() {
        return year;
    }
    
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
   
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }

    public String getUserid() {
        return userName;
    }
    
    public String getDate(){
        String day=""; 
        if (this.getDay() < 10) {
            day = "0" + this.getDay();
        }
        else {
            day = "" + this.getDay(); 
        }
        return day +"-"+this.getMonthString()+"-"+this.getYear();
    }
    
    public static int getTotalExpenseEntries() {
        return totalExpenseEntries;
    }
}
