
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;


public class ReportSelectionView extends JPanel implements java.util.Observer {
    JLabel lblEast, lblWest, lblCategory, lblMonth; 
    // Display report will open a pop up displaying budget comparisons, graphs, and option to save to file or print
    JButton btnDisplayReport, btnClear; 
    // Data type is specified to avoid Unchecked/unsafe operations compiler warning/error
    JComboBox<String> comboMonth, comboCategory;
    JPanel panelEast, panelWest, panelInfoArea, panelButtons;
    
    //images from easyicon.net
    private ImageIcon monthIcon = new ImageIcon("images/monthIcon.png");
    private ImageIcon categoryIcon = new ImageIcon("images/categoryIcon.png");
    
    Color reportPurple; 
    
    public ReportSelectionView(){
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
        reportPurple = new Color(156,39,176); 
        /* 
        lblCategory = new JLabel(" Category"); 
        reportPurple = new Color(156,39,176); 
        lblCategory.setForeground(reportPurple);
        lblCategory.setFont(new Font("Arial", Font.BOLD, 20));
        lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
        lblCategory.setIcon(categoryIcon); */ 
        
        lblMonth = new JLabel(" Month");
        lblMonth.setForeground(reportPurple);
        lblMonth.setFont(new Font("Arial", Font.BOLD, 20));
        lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonth.setIcon(monthIcon);
        
        // View expenses will create a popup detailing whatever expenses correspond with the chosen form options
        btnDisplayReport = new JButton("Display Report");
        btnClear = new JButton("Clear"); 
        // Add an expense brings up the AddExpense popup window with details to input expenses to the database
        String[] months = {"View All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "November", "December"};  
        String[] categories = {"View All", "Rent/Utilities", "Groceries", "Clothing", "Transportation/Car", "Education", "Entertainment", "Other"};  
        comboMonth = new JComboBox<String>(months);
        comboCategory = new JComboBox<String>(categories);
        
        panelInfoArea = new JPanel();
        panelInfoArea.setLayout(new GridLayout(3, 3)); 
        panelInfoArea.add(lblMonth);
        panelInfoArea.add(comboMonth);
        /*
        panelInfoArea.add(lblCategory); 
        panelInfoArea.add(comboCategory);
                */ 
        
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout()); 
        panelButtons.add(btnDisplayReport);
        panelButtons.add(btnClear); 
        
        panelEast = new JPanel();
        panelEast.add(lblEast);
        
        panelWest = new JPanel();
        panelWest.add(lblWest); 
        

    }
    
    public void clearValues() {
        comboMonth.setSelectedItem("View All"); 
        comboCategory.setSelectedItem("View All"); 
    }
    
    public void displayDisplayReportView(double[] incomeData, String[] incomeLabels, double[] budgetData, String[] budgetLabels, double[] expenseData, String[] expenseLabels) {
        view.DisplayReportView displayReportPopup = new view.DisplayReportView(incomeData, incomeLabels, budgetData, budgetLabels, expenseData, expenseLabels); 
    }
    
    public String getCategory() {
        String category = comboCategory.getSelectedItem().toString();
        return category;
    }
    
    public String getMonth() {
        String month = comboMonth.getSelectedItem().toString();
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

    
    
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Used for interacting with model
    }

    
    public void addController(ActionListener controller){
		btnDisplayReport.addActionListener(controller);	//need instance of controller before can add it as a listener 
                btnDisplayReport.setActionCommand("DisplayReport"); 
                
                btnClear.addActionListener(controller);
                btnClear.setActionCommand("Clear"); 
	} 

}
