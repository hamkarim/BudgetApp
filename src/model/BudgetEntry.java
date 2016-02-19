
package model;

public class BudgetEntry {
    private String userid; 
    private double rentPercent;
    private double groceryPercent;
    private double clothingPercent;
    private double transportationPercent;
    private double educationPercent;
    private double savingsPercent;
    private double entertainmentPercent;
    private double otherPercent;

    // Stores each user's expense data
    
    public BudgetEntry(String userid, double rentPercent, double groceryPercent, double clothingPercent, double transportationPercent, double educationPercent, double savingsPercent, double entertainmentPercent, double otherPercent) {
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

    
}
