
package view;

import model.ExpenseEntry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import java.io.*;


public class AddExpenseView extends JFrame{
    private JLabel lblDate,lblCategory,lblDescription,lblValue,lblTitle,lblWest,lblEast;
    private JTextField txtDescription;
    // private JTextField txtValue;
    JComboBox<String> comboMonth;
    private JComboBox<String> comboCategory;
    private JButton btnAdd,btnClear;
    private JPanel panelExpenseInfo,panelButtons,panelTitle,panelWest,panelEast;
    private JDatePicker picker;
    private DataOutputStream output;
    private ExpenseEntry expense;
    private String expenseDetails;
    private SpinnerNumberModel modelValue;
    private JSpinner spinValue;
    
    public AddExpenseView(){
        this.initComponents();
        this.add(panelTitle,BorderLayout.NORTH);
        this.add(panelExpenseInfo,BorderLayout.CENTER);
        this.add(panelButtons,BorderLayout.SOUTH);
        this.add(panelWest,BorderLayout.WEST);
        this.add(panelEast,BorderLayout.EAST);
        this.setTitle("Add an Expense");
        this.setSize(550,200);
        this.setResizable(false);  // Keeps window locked at 550x200, since resizing larger makes the date picker component look strange
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hide rather than exit since this is a pop up we dont want the whole program to quit if the window is closed
        this.setVisible(true);      
    }
    
    public void initComponents(){
        lblDate = new JLabel("Date of Purchase*");
        lblCategory = new JLabel("Category*");
        lblDescription = new JLabel("Description");
        lblValue = new JLabel("Value*");
        lblTitle = new JLabel("Add an Expense");
        lblWest = new JLabel(" ");
        lblEast = new JLabel(" ");
        
        txtDescription = new JTextField();
        //txtValue = new JTextField();
        
        modelValue = new SpinnerNumberModel(0.00, 0.00, 9999999999.99, 1.00); 
        spinValue = new JSpinner(modelValue); 
        
        btnAdd = new JButton("Add");
        btnClear = new JButton("Clear");
        picker = new JDateComponentFactory().createJDatePicker();

	//picker.setTextEditable(true);   Maybe better to not have text editable, to avoid incorrect user input for this field? User is forced to use calendar to select valid date
	//picker.setShowYearButtons(true);   Also may not be neccesary to have multiple buttons to change years
                   
        String[] categories = {"Rent/Utilities", "Groceries", "Clothing", "Transportation/Car", "Education", "Entertainment", "Other"};  
        comboCategory = new JComboBox<String>(categories);
        
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
        //panelExpenseInfo.add(txtValue);
        
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(btnAdd);
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
        btnAdd.addActionListener(controller);	
        btnAdd.setActionCommand("btnAdd"); 

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
    
    public String getDescription() {
        String description = txtDescription.getText();
        return description;
    }
    
    public String getCategory() {
        String category = comboCategory.getSelectedItem().toString();
        return category;
    }

    
    public void displayMessage(String messageType) {
        if (messageType == "noDate") {
            JOptionPane.showMessageDialog(null, "Date must be entered!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (messageType == "noValue") {
            JOptionPane.showMessageDialog(null, "Value must be entered!","Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (messageType == "expenseAdded") {
            JOptionPane.showMessageDialog(null, "Expense Added Successfully!", "Expense Added", JOptionPane.INFORMATION_MESSAGE);
        }
    }
   
}
