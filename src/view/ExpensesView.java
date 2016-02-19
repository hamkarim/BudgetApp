/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import model.ExpenseStatus;

public class ExpensesView extends JPanel implements java.util.Observer {
    
    JLabel lblEast, lblWest, lblCategory, lblMonth; 
    JButton btnDisplay, btnAddExpense, btnClear; 
    // Data type is specified to avoid Unchecked/unsafe operations compiler warning/error
    
    JComboBox<String> comboMonth, comboCategory;
    JPanel panelEast, panelWest, panelInfoArea, panelButtons;
    
    //images from easyicon.net
    private ImageIcon monthIcon = new ImageIcon("images/monthIcon.png");
    private ImageIcon categoryIcon = new ImageIcon("images/categoryIcon.png");
    
    
    public ExpensesView(){
     this.setLayout(new BorderLayout());
     this.initComponents(); 
     this.add(panelInfoArea, BorderLayout.CENTER);
     this.add(panelButtons, BorderLayout.SOUTH);
     this.add(panelEast, BorderLayout.EAST);
     this.add(panelWest, BorderLayout.WEST); 
     this.setVisible(true);
    }
    
    private void initComponents() {
        lblEast = new JLabel("                ");
        lblWest = new JLabel("                ");
        
        lblCategory = new JLabel(" Category"); 
        lblCategory.setForeground(Color.red);
        lblCategory.setFont(new Font("Arial", Font.BOLD, 20));
        lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
        lblCategory.setIcon(categoryIcon);
        
        lblMonth = new JLabel(" Month");
        lblMonth.setForeground(Color.red);
        lblMonth.setFont(new Font("Arial", Font.BOLD, 20));
        lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonth.setIcon(monthIcon);
        
        // View expenses will create a popup detailing whatever expenses correspond with the chosen form options
        btnDisplay = new JButton("View Expenses");
        // Add an expense brings up the AddExpense popup window with details to input expenses to the database
        btnAddExpense = new JButton("Add an Expense");
        btnClear = new JButton("Clear"); 
        
        String[] months = {"View All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "November", "December"};  
        String[] categories = {"View All", "Rent/Utilities", "Groceries", "Clothing", "Transportation/Car", "Education", "Entertainment", "Other"};  
        comboMonth = new JComboBox<String>(months);
        comboCategory = new JComboBox<String>(categories);
        
        panelInfoArea = new JPanel();
        panelInfoArea.setLayout(new GridLayout(4,2,10,55)); 
        panelInfoArea.add(lblMonth);
        panelInfoArea.add(comboMonth);
        panelInfoArea.add(lblCategory); 
        panelInfoArea.add(comboCategory);
        
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout()); 
        panelButtons.add(btnDisplay);
        panelButtons.add(btnClear); 
        panelButtons.add(btnAddExpense);
        
        panelEast = new JPanel();
        panelEast.add(lblEast);
        
        panelWest = new JPanel();
        panelWest.add(lblWest); 
        
           
        
       
    }
    
    public void addController(ActionListener controller){
        // Adding event handlers to the menu and nav bar items
        btnDisplay.addActionListener(controller);	
        btnDisplay.setActionCommand("btnDisplay"); 

        btnAddExpense.addActionListener(controller);	
        btnAddExpense.setActionCommand("btnAddExpense");     
        
        btnClear.addActionListener(controller);
        btnClear.setActionCommand("btnClear"); 
    } 
    
    public String getCategory() {
        String category = comboCategory.getSelectedItem().toString();
        return category;
    }
    
    public String getMonth() {
        String month = getShortMonth(comboMonth.getSelectedItem().toString());
        return month;
    }

    public String getShortMonth(String month){
        String shortMonth="";
        switch(month){
            case "January":
                shortMonth = "Jan";
                break;
            case "February":
                shortMonth = "Feb";
                break;
            case "March":
                shortMonth = "Mar";
                break;
            case "April":
                shortMonth = "Apr";
                break;
            case "May":
                shortMonth = "May";
                break;
            case "June":
                shortMonth ="Jun";
                break;
            case "July":
                shortMonth = "Jul";
                break;
            case "August":
                shortMonth = "Aug";
                break;
            case "September":
                shortMonth = "Sep";
                break;
            case "October":
                shortMonth = "Oct";
                break;
            case "November":
                shortMonth = "Nov";
                break;
            case "December":
                shortMonth = "Dec";
                break;
            default:
                shortMonth = "View All";
                break;
        }
        return shortMonth;
    }
    
    public void displayExpenses(String category,String month,Object[][] rowData) {
        DisplayExpensesView displayExpensesPopUp = new DisplayExpensesView(category,month,rowData); 
        controller.DisplayExpensesController displayExpensesController = new controller.DisplayExpensesController();
        displayExpensesController.addView(displayExpensesPopUp);
        displayExpensesPopUp.addController(displayExpensesController);
        ExpenseStatus expenseModel = new ExpenseStatus(); 
        displayExpensesController.addModel(expenseModel);
    }
    
    public void displayNoExpenses() {
        JOptionPane.showMessageDialog(null, "There are no expense entries for the selected period/category.", "No Expenses", JOptionPane.ERROR_MESSAGE);
    }
    
    public void displayAddExpense() {
        AddExpenseView addExpensePopUp = new AddExpenseView(); 
        controller.AddExpenseController addExpenseController = new controller.AddExpenseController();
        addExpenseController.addView(addExpensePopUp);
        addExpensePopUp.addController(addExpenseController);
        ExpenseStatus expenseModel = new ExpenseStatus(); 
        addExpenseController.addModel(expenseModel);
    }

    public void clearValues() {
        comboMonth.setSelectedItem("View All"); 
        comboCategory.setSelectedItem("View All"); 
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

     
    

    
   


