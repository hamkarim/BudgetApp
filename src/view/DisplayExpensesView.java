
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.ExpenseEntry;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import model.ExpenseStatus;
import model.FileIO;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class DisplayExpensesView extends JFrame{
    private JLabel lblTitle; 
    private JPanel panelExpenseInfo, panelTitle, panelButtons; 
    private JTable tblExpenses;
    private JScrollPane scrollPane;
    private JButton btnEdit,btnDelete;
    private ExpenseEntry expense;
    private final String columnNames[] = { "User ID","Expense ID","Date of Purchase", "Category", "Description", "Value"};
    private DefaultTableModel tm;
    private String category,month;
    private EditExpenseView editExpensePopUp;
    
    
    //Test by Lynn
    public DisplayExpensesView(String category,String month,Object rowData[][]){
        this.category = category;
        this.month = month;
        this.initComponents(rowData);
        this.add(panelTitle,BorderLayout.NORTH);
        this.add(panelExpenseInfo,BorderLayout.CENTER);
        this.add(panelButtons,BorderLayout.SOUTH);
        this.setTitle("View Expenses");
        this.setSize(800,600);
        this.setResizable(false);  // Keeps window locked at 800x600, since resizing doesn't work well with the JTable
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hide rather than exit since this is a pop up we dont want the whole program to quit if the window is closed
        this.setVisible(true);      
    }
    
    public void initComponents(Object rowData[][]){
        lblTitle = new JLabel("Expenses Log");
        
        // Data will be retrieved from database depending on which expenses the user has selected to view (based on the form on the expenses page)
        
        
        // Need to look into making the cells non editable, make format more presentable, best way to import data into this table
        
        tm = new DefaultTableModel(rowData, columnNames);
        tblExpenses = new JTable(tm) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {                
                    return false;               
            };
        };
        this.hideColumn(tblExpenses, 0);
        this.hideColumn(tblExpenses, 1);
        scrollPane = new JScrollPane(tblExpenses);
        tblExpenses.setPreferredScrollableViewportSize(new Dimension(750, 450));
        
        // Container for table
        panelExpenseInfo = new JPanel();
        panelExpenseInfo.add(scrollPane); 

        panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout());
        panelTitle.add(lblTitle);
        
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        
    }
    
    public void addController(ActionListener controller){
        btnEdit.addActionListener(controller);	
        btnEdit.setActionCommand("btnEdit"); 

        btnDelete.addActionListener(controller);	
        btnDelete.setActionCommand("btnDelete");     
         
    } 
    
    private void deleteExpenseItem(String userId, int expenseId, int day, int month, int year, String description, String category, double value){
        FileIO.deleteExpense(userId, expenseId, day, month, year, description, category, value);
    }
    
    private int getDay(String date){
        return Integer.parseInt(date.substring(0, 2));
    }
    private int getMonth(String date){
         date = date.substring(3, 6);
         return getIntMonth(date);         
    }
    private int getYear(String date){
        return Integer.parseInt(date.substring(7));
    }
    
    public int getIntMonth(String monthString) {
        int month = 0;
        switch(monthString){
            case "Jan": 
                month=1;
                break;
            case "Feb":
                month=2;
                break;
            case "Mar":
                month=3;
                break;
            case "Apr":
                month=4;
                break;
            case "May":
                month=5;
                break;
            case "Jun":
                month=6;
                break;
            case "Jul":
                month=7;
                break;
            case "Aug":
                month=8;
                break;
            case "Sep":
                month=9;
                break;
            case "Oct":
                month=10;
                break;
            case "Nov":
                month=11;
                break;
            case "Dec":
                month=12;
                break;
            default:
                month=0;
        }
        return month;
    }
    
    protected void hideColumn(JTable table,int index){ 
        TableColumn tc= table.getColumnModel().getColumn(index); 
        tc.setMaxWidth(0); 
        tc.setPreferredWidth(0); 
        tc.setMinWidth(0); 
        tc.setWidth(0); 
        table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0); 
        table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0); 
    }
    
    public ExpenseEntry getSelectedItem(){
        int row = tblExpenses.getSelectedRow();
        String userID = tblExpenses.getValueAt(row,0).toString();
        int expenseID = Integer.parseInt(tblExpenses.getValueAt(row, 1).toString());
        String date = tblExpenses.getValueAt(row, 2).toString();
        String category = tblExpenses.getValueAt(row, 3).toString();
        String description = tblExpenses.getValueAt(row, 4).toString();
        Double value = Double.parseDouble(tblExpenses.getValueAt(row, 5).toString());
        int day = getDay(date);
        int month = getMonth(date);
        int year = getYear(date);
        ExpenseEntry selectedItem = new ExpenseEntry(expenseID,userID,day,month,year,description,category,value);
        return selectedItem;
    }
    
    
    public void displayEidtExpense() {
        //EditExpenseView 
        editExpensePopUp = new EditExpenseView(getSelectedItem().getUserid(),getSelectedItem().getExpenseID(),getSelectedItem().getDay(),
                getSelectedItem().getMonth(),getSelectedItem().getYear(),getSelectedItem().getCategory(),getSelectedItem().getDescription(),
                getSelectedItem().getValue());
        controller.EditExpenseController editExpenseController = new controller.EditExpenseController();
        editExpenseController.addView(editExpensePopUp);
        editExpensePopUp.addController(editExpenseController);
        ExpenseStatus expenseModel = new ExpenseStatus(); 
        editExpenseController.addModel(expenseModel);
        editExpensePopUp.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                refreshExpense(ExpenseStatus.expenseTableFormat(ExpenseStatus.filterExpenses(getCategory(), getMonth())));
            }
        });
    }
    
    public void displayMessage(String messageType) {
        if (null != messageType) switch (messageType) {
            case "noSelectedItem":
                JOptionPane.showMessageDialog(null, "An item must be selected!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "moreSelectedItem":
                JOptionPane.showMessageDialog(null, "Please select one row at a time", messageType, WIDTH);
                break;
            case "expenseDeleted":
                JOptionPane.showMessageDialog(null, "Expense Deleted Successfully!", "Expense Added", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    
    public boolean isItemSelected(){
        return this.tblExpenses.getSelectedRowCount()==1;
    }
    
    public void refreshExpense(Object data[][]){
        tm = new DefaultTableModel(data,columnNames);
        this.tblExpenses.setModel(tm);
        this.hideColumn(tblExpenses, 0);
        this.hideColumn(tblExpenses, 1);   
    }
    
    public String getCategory(){
        return this.category;
    }
    
    public String getMonth(){
        return this.month;
    }
    
    public EditExpenseView getEditExpensePopUp(){
        return this.editExpensePopUp;
    }

}


