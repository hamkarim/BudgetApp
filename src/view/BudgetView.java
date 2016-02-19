/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BudgetView extends JPanel implements java.util.Observer  {
    JLabel lblEast, lblWest;
            
    JLabel lblCategory, lblPercentage, lblValue, lblRent, lblGroceries, lblClothing, lblTransportation, lblEducation, lblSavings, lblEntertainment, lblOther, lblTotalIncome;
    JSpinner spinRentPer, spinGroceriesPer, spinClothingPer, spinTransportationPer, spinEducationPer, spinSavingsPer, spinEntertainmentPer, spinOtherPer; 
    SpinnerNumberModel modelRentPer, modelGroceriesPer, modelClothingPer, modelTransportationPer, modelEducationPer, modelSavingsPer, modelEntertainmentPer, modelOtherPer; 

    JTextField txtRentVal, txtGroceriesVal, txtClothingVal, txtTransportationVal, txtEducationVal, txtSavingsVal, txtEntertainmentVal, txtOtherVal, txtTotalPer, txtTotalVal;
    
    
    JButton btnUpdate, btnClear;
     
    JPanel panelEast, panelWest, panelButtons, panelInfoArea;
        
    public BudgetView(){
     this.setLayout(new BorderLayout());
     this.initComponents();
     this.add(panelInfoArea, BorderLayout.CENTER);
     this.add(panelButtons, BorderLayout.SOUTH);
     this.add(panelEast, BorderLayout.EAST);
     this.add(panelWest, BorderLayout.WEST); 
     this.setSize(800,600);
     this.setVisible(true);
     
     
    }
    
    private void initComponents() {
        lblEast = new JLabel(" ");
        lblWest = new JLabel(" ");
        
        lblCategory=new JLabel("Category of Expenses");
        lblPercentage= new JLabel("Percentage of Income");
        lblValue= new JLabel("Monthly Value"); 
        lblRent= new JLabel("Rent/Utilities"); 
        lblGroceries = new JLabel("Groceries");
        lblClothing = new JLabel("Clothing");
        lblTransportation = new JLabel("Transportation/Car");
        lblEducation = new JLabel("Education");
        lblEntertainment = new JLabel("Entertainment");
        lblSavings = new JLabel("Savings");
        lblOther = new JLabel("Other");
        lblTotalIncome = new JLabel("Total Income"); 
        
        
        lblCategory.setForeground(Color.blue);
        lblCategory.setFont(new Font("Arial", Font.BOLD, 20));
        lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblPercentage.setForeground(Color.blue);
        lblPercentage.setFont(new Font("Arial", Font.BOLD, 20));
        lblPercentage.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblValue.setForeground(Color.blue);
        lblValue.setFont(new Font("Arial", Font.BOLD, 20));
        lblValue.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblTotalIncome.setForeground(Color.blue);
        
        lblRent.setFont(new Font("Arial", Font.BOLD, 15));
        lblGroceries.setFont(new Font("Arial", Font.BOLD, 15));
        lblClothing.setFont(new Font("Arial", Font.BOLD, 15));
        lblTransportation.setFont(new Font("Arial", Font.BOLD, 15));
        lblEducation.setFont(new Font("Arial", Font.BOLD, 15));
        lblEntertainment.setFont(new Font("Arial", Font.BOLD, 15));
        lblSavings.setFont(new Font("Arial", Font.BOLD, 15));
        lblOther.setFont(new Font("Arial", Font.BOLD, 15));
        lblTotalIncome.setFont(new Font("Arial", Font.BOLD, 20));
        
        lblRent.setHorizontalAlignment(SwingConstants.CENTER);
        lblGroceries.setHorizontalAlignment(SwingConstants.CENTER);
        lblClothing.setHorizontalAlignment(SwingConstants.CENTER);
        lblTransportation.setHorizontalAlignment(SwingConstants.CENTER);
        lblEducation.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntertainment.setHorizontalAlignment(SwingConstants.CENTER);
        lblSavings.setHorizontalAlignment(SwingConstants.CENTER);
        lblOther.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalIncome.setHorizontalAlignment(SwingConstants.CENTER);
        
        modelRentPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinRentPer = new JSpinner(modelRentPer); 
        txtRentVal = new JTextField();
        txtRentVal.setEnabled(false);
        
        modelGroceriesPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinGroceriesPer = new JSpinner(modelGroceriesPer); 
        txtGroceriesVal = new JTextField();
        txtGroceriesVal.setEnabled(false);
        
        modelClothingPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinClothingPer = new JSpinner(modelClothingPer); 
        txtClothingVal = new JTextField();
        txtClothingVal.setEnabled(false);
        
        modelTransportationPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinTransportationPer = new JSpinner(modelTransportationPer); 
        txtTransportationVal = new JTextField();
        txtTransportationVal.setEnabled(false);
        
        
        modelEducationPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinEducationPer = new JSpinner(modelEducationPer); 
        txtEducationVal = new JTextField();
        txtEducationVal.setEnabled(false);
        
        modelEntertainmentPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinEntertainmentPer = new JSpinner(modelEntertainmentPer); 
        txtEntertainmentVal = new JTextField();
        txtEntertainmentVal.setEnabled(false); 
        
        modelSavingsPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinSavingsPer = new JSpinner(modelSavingsPer); 
        txtSavingsVal = new JTextField();
        txtSavingsVal.setEnabled(false);
        
        modelOtherPer = new SpinnerNumberModel(0.0, 0.0, 100.0, 1.0);  
        spinOtherPer = new JSpinner(modelOtherPer); 
        txtOtherVal = new JTextField();
        txtOtherVal.setEnabled(false);
        
        txtTotalPer = new JTextField("");
        txtTotalVal = new JTextField(); 
        txtTotalPer.setEnabled(false);
        txtTotalVal.setEnabled(false);
        
        
        panelInfoArea = new JPanel();
        panelInfoArea.setLayout(new GridLayout(10, 3)); 
        panelInfoArea.add(lblCategory); 
        panelInfoArea.add(lblPercentage);
        panelInfoArea.add(lblValue);
        panelInfoArea.add(lblRent);
        panelInfoArea.add(spinRentPer);
        panelInfoArea.add(txtRentVal);
        panelInfoArea.add(lblGroceries);
        panelInfoArea.add(spinGroceriesPer);
        panelInfoArea.add(txtGroceriesVal);
        panelInfoArea.add(lblClothing);
        panelInfoArea.add(spinClothingPer);
        panelInfoArea.add(txtClothingVal);
        panelInfoArea.add(lblTransportation);
        panelInfoArea.add(spinTransportationPer);
        panelInfoArea.add(txtTransportationVal);
        panelInfoArea.add(lblEducation);
        panelInfoArea.add(spinEducationPer);
        panelInfoArea.add(txtEducationVal);
        panelInfoArea.add(lblEntertainment);
        panelInfoArea.add(spinEntertainmentPer);
        panelInfoArea.add(txtEntertainmentVal);
        panelInfoArea.add(lblSavings);
        panelInfoArea.add(spinSavingsPer);
        panelInfoArea.add(txtSavingsVal);
        panelInfoArea.add(lblOther);
        panelInfoArea.add(spinOtherPer);
        panelInfoArea.add(txtOtherVal);
        panelInfoArea.add(lblTotalIncome);
        panelInfoArea.add(txtTotalPer); 
        panelInfoArea.add(txtTotalVal);
        panelInfoArea.setBackground(Color.pink);
        panelInfoArea.setFont(new Font("ArialBlack", Font.BOLD, 16));
        
           
        btnUpdate = new JButton("Update");
        btnClear = new JButton("Clear"); 
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout()); 
        panelButtons.add(btnUpdate);
        panelButtons.add(btnClear);
        
         
        panelEast = new JPanel();
        panelEast.add(lblEast);
        
        
        panelWest = new JPanel();
        panelWest.add(lblWest); 
        
        spinRentPer.addChangeListener(new Changes());
        spinGroceriesPer.addChangeListener(new Changes());
        spinClothingPer.addChangeListener(new Changes());
        spinTransportationPer.addChangeListener(new Changes()); 
        spinEducationPer.addChangeListener(new Changes());
        spinSavingsPer.addChangeListener(new Changes());
        spinEntertainmentPer.addChangeListener(new Changes());
        spinOtherPer.addChangeListener(new Changes());         
    }
    
    public void addController(ActionListener controller){
        // Adding event handlers to the menu and nav bar items
        btnClear.addActionListener(controller);	
        btnClear.setActionCommand("btnClear"); 

        btnUpdate.addActionListener(controller);	
        btnUpdate.setActionCommand("btnUpdate");     
        
    } 

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double getTotalPer() {
        double totalPer = (double)spinRentPer.getValue() + (double)spinGroceriesPer.getValue() + (double)spinClothingPer.getValue() + (double)spinTransportationPer.getValue() +
                            (double)spinEducationPer.getValue() + (double)spinEntertainmentPer.getValue() + (double)spinSavingsPer.getValue() + (double)spinOtherPer.getValue(); 
        return totalPer;             
    }
    
    public double getRentPercent() {
        return (double)spinRentPer.getValue();
    }
    
    public double getGroceryPercent() {
        return (double)spinGroceriesPer.getValue(); 
    }
    
    public double getClothingPercent() {
        return (double)spinClothingPer.getValue();
    }
    
    public double getTransportationPercent() {
        return (double)spinTransportationPer.getValue();
    }
    
    public double getEducationPercent() {
        return (double)spinEducationPer.getValue();
    }
    
    public double getEntertainmentPercent(){ 
        return (double)spinEntertainmentPer.getValue();
    }
    
    public double getSavingsPercent() {
        return (double)spinSavingsPer.getValue();
    }
    
    public double getOtherPercent() {
        return (double)spinOtherPer.getValue();
    }
    
    public void showMessage(String type) {
        if (type == "incorrectTotalPer") {
            JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if (type == "budgetEntrySuccessful") {
            JOptionPane.showMessageDialog(null, "Budget targets updated successfully.", "Budget Update Successful", JOptionPane.INFORMATION_MESSAGE);                        
        }
    }

    // Allows for dynamic updating of values based on percentage and total income 
    // Handler kept in view because updates are only concerned with display and do not affect the model layer
    
    private class Changes implements ChangeListener{
        double totalValPer,rentVal,groceriesVal,clothingVal,transportationVal,educationVal,entertainmentVal,savingsVal,otherVal;
        public void stateChanged(ChangeEvent e) {
            if (!(txtTotalVal.getText().isEmpty())) {
                if(e.getSource()==spinRentPer){
                    totalValPer = getTotalPer();
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinRentPer.setValue(0.0);
                    }else{                   
                        txtTotalPer.setText(totalValPer+"");
                        rentVal = (double)spinRentPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtRentVal.setText(rentVal+"");
                    }
                }else if(e.getSource()==spinGroceriesPer){
                    totalValPer = getTotalPer();
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinGroceriesPer.setValue(0.0);
                    }else{
                        txtTotalPer.setText(totalValPer+"");
                        groceriesVal = (double)spinGroceriesPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtGroceriesVal.setText(groceriesVal+"");
                    }
                }else if(e.getSource()==spinClothingPer){
                    totalValPer = getTotalPer();
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinClothingPer.setValue(0.0);
                    }else{
                        txtTotalPer.setText(totalValPer+"");
                        clothingVal = (double)spinClothingPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtClothingVal.setText(clothingVal+"");
                    }
                }else if(e.getSource()==spinTransportationPer){
                    totalValPer = getTotalPer();
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinTransportationPer.setValue(0.0);
                    }else{
                        txtTotalPer.setText(totalValPer+"");
                        transportationVal = (double)spinTransportationPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtTransportationVal.setText(transportationVal+"");
                    }
                }else if(e.getSource()==spinEducationPer){
                    totalValPer = getTotalPer();
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinEducationPer.setValue(0.0);
                    }else{
                        txtTotalPer.setText(totalValPer+"");
                        educationVal = (double)spinEducationPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtEducationVal.setText(educationVal+"");
                    }
                }else if(e.getSource()==spinEntertainmentPer){
                    totalValPer = getTotalPer();
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinEntertainmentPer.setValue(0.0);
                    }else{
                        txtTotalPer.setText(totalValPer+"");
                        entertainmentVal = (double)spinEntertainmentPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtEntertainmentVal.setText(entertainmentVal+"");
                    }
                }else if(e.getSource()==spinSavingsPer){
                    totalValPer = getTotalPer();
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinSavingsPer.setValue(0.0);
                    }else{
                        txtTotalPer.setText(totalValPer+"");
                        savingsVal = (double)spinSavingsPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtSavingsVal.setText(savingsVal+"");
                    }
                }else if(e.getSource()==spinOtherPer){
                    totalValPer = getTotalPer(); 
                    if(totalValPer > 100.0){
                        JOptionPane.showMessageDialog(null, "Error - All budget percentage fields must add up to 100%!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        spinOtherPer.setValue(0.0);
                    }else{
                        txtTotalPer.setText(totalValPer+"");
                        otherVal = (double)spinOtherPer.getValue()/100*Double.parseDouble(txtTotalVal.getText());
                        txtOtherVal.setText(otherVal+"");
                    }
                }
            }
        }
    }
 
    
    public void clearValues() {
        spinRentPer.setValue(0.0);
        txtRentVal.setText("0.0");
        spinGroceriesPer.setValue(0.0);
        txtClothingVal.setText("0.0");
        spinClothingPer.setValue(0.0);
        txtGroceriesVal.setText("0.0"); 
        spinTransportationPer.setValue(0.0);
        txtTransportationVal.setText("0.0"); 
        spinEducationPer.setValue(0.0);
        txtEducationVal.setText("0.0"); 
        spinEntertainmentPer.setValue(0.0);
        txtEntertainmentVal.setText("0.0"); 
        spinSavingsPer.setValue(0.0);
        txtSavingsVal.setText("0.0"); 
        spinOtherPer.setValue(0.0);
        txtOtherVal.setText("0.0"); 
        txtTotalPer.setText("0.0"); 
        txtTotalVal.setText("0.0"); 
    }
    
    
    // Updates the values based on the user's income entries
    public void setValues(double totalIncome, double rent, double groceries, double clothing, double transportation, double education, double entertainment, double savings, double other) {
            txtTotalVal.setText(Double.toString(totalIncome)); 
            txtRentVal.setText(Double.toString((rent / 100) * totalIncome)); 
            txtGroceriesVal.setText(Double.toString((groceries / 100) * totalIncome)); 
            txtClothingVal.setText(Double.toString((clothing / 100) * totalIncome)); 
            txtTransportationVal.setText(Double.toString((transportation / 100) * totalIncome)); 
            txtEducationVal.setText(Double.toString((education / 100) * totalIncome)); 
            txtEntertainmentVal.setText(Double.toString((entertainment / 100) * totalIncome)); 
            txtSavingsVal.setText(Double.toString((savings / 100) * totalIncome)); 
            txtOtherVal.setText(Double.toString((other / 100) * totalIncome)); 
        }
    
     
    
    public void setBudgetPercentages(double rent, double groceries, double clothing, double transportation, double education, double entertainment, double savings, double other) {
            spinRentPer.setValue(rent); 
            spinGroceriesPer.setValue(groceries); 
            spinClothingPer.setValue(clothing);
            spinTransportationPer.setValue(transportation);
            spinEducationPer.setValue(education); 
            spinSavingsPer.setValue(savings);
            spinEntertainmentPer.setValue(entertainment);
            spinOtherPer.setValue(other);
            double total = 100;
            txtTotalPer.setText(Double.toString(total)); 
        }
    
  

}
