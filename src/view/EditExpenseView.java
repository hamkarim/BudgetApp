
package view;

import model.ExpenseEntry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import java.io.*;

public class EditExpenseView extends JFrame{
    private JLabel lblDate,lblCategory,lblDescription,lblValue,lblTitle,lblWest,lblEast;
    private JTextField txtDescription;
    JComboBox<String> comboMonth;
    private JComboBox<String> comboCategory;
    private JButton btnUpdate,btnClear;
    private JPanel panelExpenseInfo,panelButtons,panelTitle,panelWest,panelEast;
    private JDatePicker picker;
    private DataOutputStream output;
    private ExpenseEntry expense;
    private String expenseDetails;
    private SpinnerNumberModel modelValue;
    private JSpinner spinValue;
    private String initUserID,initDescription,initCategory;
    private int iniExpenseID,initDay,initMonth,initYear;
    private double initValue;
    
    public EditExpenseView(String userID,int expenseID,int day,int month,int year,String category,String description,double value){
        this.initComponents(userID,expenseID,day,month,year,category,description,value);
        this.add(panelTitle,BorderLayout.NORTH);
        this.add(panelExpenseInfo,BorderLayout.CENTER);
        this.add(panelButtons,BorderLayout.SOUTH);
        this.add(panelWest,BorderLayout.WEST);
        this.add(panelEast,BorderLayout.EAST);
        this.setTitle("Edit an Expense");
        this.setSize(550,200);
        this.setResizable(false);  // Keeps window locked at 550x200, since resizing larger makes the date picker component look strange
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // hide rather than exit since this is a pop up we dont want the whole program to quit if the window is closed
        this.setVisible(true);      
        
        //store initValues
        this.initUserID = userID;
        this.iniExpenseID = expenseID;
        this.initDay = day;
        this.initMonth = month;
        this.initYear = year;
        this.initCategory = category;
        this.initDescription = description;
        this.initValue = value;
        
        
    }
    
    public void initComponents(String userID,int expenseID,int day,int month,int year,String category,String description,double value){
        lblDate = new JLabel("Date of Purchase*");
        lblCategory = new JLabel("Category*");
        lblDescription = new JLabel("Description");
        lblValue = new JLabel("Value*");
        lblTitle = new JLabel("Edit an Expense");
        lblWest = new JLabel(" ");
        lblEast = new JLabel(" ");
        
        txtDescription = new JTextField(description);
        
        modelValue = new SpinnerNumberModel(0.00, 0.00, 9999999999.99, 1.00); 
        spinValue = new JSpinner(modelValue); 
        spinValue.setValue(value);
        
        btnUpdate = new JButton("Update");
        btnClear = new JButton("Clear");
        picker = new JDateComponentFactory().createJDatePicker();
        picker.getModel().setDate(year, month-1, day);  
        

	//picker.setTextEditable(true);   Maybe better to not have text editable, to avoid incorrect user input for this field? User is forced to use calendar to select valid date
	//picker.setShowYearButtons(true);   Also may not be neccesary to have multiple buttons to change years
                   
        String[] categories = {"Rent/Utilities", "Groceries", "Clothing", "Transportation/Car", "Education", "Entertainment", "Other"};  
        comboCategory = new JComboBox<String>(categories);
        comboCategory.setSelectedItem(category);
        
        panelExpenseInfo = new JPanel();
        panelExpenseInfo.setLayout(new GridLayout(4,2));
        panelExpenseInfo.add(lblDate);
        panelExpenseInfo.add((JComponent) picker);
        panelExpenseInfo.add(lblCategory);
        panelExpenseInfo.add(comboCategory);
        panelExpenseInfo.add(lblDescription);
        panelExpenseInfo.add(txtDescription);
        panelExpenseInfo.add(lblValue);
        panelExpenseInfo.add(spinValue); 
        
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(btnUpdate);
        panelButtons.add(btnClear);
        
        panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout());
        panelTitle.add(lblTitle);
        
        panelWest = new JPanel();
        panelWest.setLayout(new FlowLayout());
        panelWest.add(lblWest);
        
        panelEast = new JPanel();
        panelEast.setLayout(new FlowLayout());
        panelEast.add(lblEast);
        
 
    }
       
    public void addController(ActionListener controller){
        btnUpdate.addActionListener(controller);	
        btnUpdate.setActionCommand("btnUpdate"); 

        btnClear.addActionListener(controller);	
        btnClear.setActionCommand("btnClear");             
    } 
    
    public void clearValues() {
        txtDescription.setText(""); 
        modelValue.setValue(0.0);
        comboCategory.setSelectedItem("Rent/Utilities"); 
        // Resets date on picker to current date
        Calendar calendar = Calendar.getInstance();                   
        picker.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));  
        
    }
    
    public boolean isDateSelected() {
       return picker.getModel().isSelected(); 
    }
    
    public double getValue() {
        return (Double)modelValue.getValue(); 
    }
    
    public String getDescription() {
        String description = txtDescription.getText();
        return description;
    }
    
    public String getCategory() {
        String category = comboCategory.getSelectedItem().toString();
        return category;
    }
    
    public void displayMessage(String messageType) {
        switch (messageType) {
            case "noDate":
                JOptionPane.showMessageDialog(null, "Date must be entered!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "noValue":
                JOptionPane.showMessageDialog(null, "Value must be entered!","Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "expenseUpdated":
                JOptionPane.showMessageDialog(null, "Expense Updated Successfully!", "Expense Updated", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    
    public int getExpenseID(){
        return this.iniExpenseID;
    }
        
    public int getDay() {
        int day = picker.getModel().getDay(); 
        return day;
    }
    
    public int getMonth() {
        int month = picker.getModel().getMonth() + 1; 
        return month;
    }
    
    public int getYear() {
        int year = picker.getModel().getYear();
        return year;
    }
    
    
}
