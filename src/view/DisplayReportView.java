
package view;

import ChartDirector.ChartViewer;
import ChartDirector.PieChart;
import javax.swing.*;
import java.awt.*;
import static javax.swing.BorderFactory.createEmptyBorder;

public class DisplayReportView extends JFrame{
    private JLabel lblTitle, lblEast, lblWest;
    private JPanel panelReport, panelTitle, panelEast, panelWest;
    private JTextArea lblIncome, lblExpenses, lblBudget;
    private ChartViewer incomeViewer, expenseViewer, budgetViewer; 
    PieChart incomeChart, expenseChart, budgetChart;
    String[] incomeLabels, budgetLabels;
    JScrollPane sp; 
    
    public DisplayReportView(double[] incomeData, String[] incomeLabels, double[] budgetData, String[] budgetLabels, double[] expenseData, String[] expenseLabels){
        this.initComponents(incomeData, incomeLabels, budgetData, budgetLabels, expenseData, expenseLabels);
        this.add(panelTitle,BorderLayout.NORTH);
        this.add(panelReport,BorderLayout.CENTER);
        this.add(panelEast, BorderLayout.EAST);
        this.add(panelWest, BorderLayout.WEST); 
        this.setTitle("Report");
        this.setSize(1200,800);
        this.setResizable(false);  // Keeps window locked at 550x200, since resizing larger makes the date picker component look strange
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hide rather than exit since this is a pop up we dont want the whole program to quit if the window is closed
        this.setVisible(true);      
    }
    
    public void initComponents(double[] incomeData, String[] incomeLabels, double[] budgetData, String[] budgetLabels, double[] expenseData, String[] expenseLabels){
        lblTitle = new JLabel();
        lblIncome = new JTextArea(); 
        lblExpenses = new JTextArea();
        lblBudget = new JTextArea(); 
        

        sp = new JScrollPane(lblExpenses);
        sp.setBorder(createEmptyBorder());
        
        lblIncome.setEditable(false); 
        lblExpenses.setEditable(false);
        lblBudget.setEditable(false); 
        
        lblIncome.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblBudget.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblExpenses.setFont(new Font("Monospaced", Font.PLAIN, 12));

        
        lblEast = new JLabel("        "); 
        lblWest = new JLabel("        "); 
        

        panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout());
        panelTitle.add(lblTitle);
        
        panelReport = new JPanel();
        panelReport.setLayout(new GridLayout(3, 2));
        panelReport.add(lblIncome); 
        
        
        
        // Create the pie charts
        incomeViewer = new ChartViewer();
        incomeChart = new PieChart(600, 400); 
        incomeChart.setPieSize(300, 190, 75); 
        incomeChart.setData(incomeData, incomeLabels);
        incomeViewer.setChart(incomeChart);
        
        budgetViewer = new ChartViewer();
        budgetChart = new PieChart(600, 400); 
        budgetChart.setPieSize(300, 190, 75); 
        budgetChart.setData(budgetData, budgetLabels);
        budgetViewer.setChart(budgetChart);
        
        expenseViewer = new ChartViewer();
        expenseChart = new PieChart(600, 400); 
        expenseChart.setPieSize(300, 190, 75); 
        expenseChart.setData(expenseData, expenseLabels);
        expenseViewer.setChart(expenseChart);
        
        panelReport.add(incomeViewer);
        panelReport.add(lblBudget); 
        panelReport.add(budgetViewer);
        panelReport.add(sp);
        panelReport.add(expenseViewer); 
        
        panelEast = new JPanel();
        panelEast.add(lblEast);
        
        panelWest = new JPanel();
        panelWest.add(lblWest);

    }
    
    
    
    public void setHeading(String user, String month) {
        String title = String.format("<html><body align='center'>" + user + " - Financial Report<br>Month: " + month + "<br></body></html>"); 
        lblTitle.setText(title); 
    }
    
    public void setReports(String income, String budget, String expenses) {
        lblIncome.setText(income);
        lblBudget.setText(budget);
        lblExpenses.setText(expenses); 
        lblExpenses.setCaretPosition(0); 
    }
    
    
    

    
}
