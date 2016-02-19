/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;
import java.awt.*;

public class NavBarView extends JPanel {
    
    
    // Protected to allow subclasses to call setEnabled method on nav bar buttons depending on the current window
    protected JButton btnBudget,btnIncome, btnExpenses,btnReport, btnLogout; 
    
    // Icons are the property of flaticon.com, used with permission 
    private ImageIcon budgetIcon = new ImageIcon("images/budget.png"); 
    private ImageIcon incomeIcon = new ImageIcon("images/income.png"); 
    private ImageIcon expensesIcon = new ImageIcon("images/expenses.png");
    private ImageIcon reportIcon = new ImageIcon("images/report.png"); 
    private ImageIcon logoutIcon = new ImageIcon("images/logout.png");
   // protected JPanel panelNavBar;
    

    
    public NavBarView() {
        this.initComponents();
        this.setLayout(new FlowLayout());
        this.add(btnBudget);
        this.add(btnIncome);
        this.add(btnExpenses);
        this.add(btnReport);
        this.add(btnLogout);   
    }

    private void initComponents() {
        budgetIcon = new ImageIcon("images/budget.png"); 
        incomeIcon = new ImageIcon("images/income.png"); 
        expensesIcon = new ImageIcon("images/expenses.png");
        reportIcon = new ImageIcon("images/report.png"); 
        logoutIcon = new ImageIcon("images/logout.png");
        btnBudget = new JButton("Budget", budgetIcon);
        btnIncome= new JButton("Income", incomeIcon);
        btnExpenses= new JButton ("Expenses", expensesIcon);
        btnReport= new JButton ("Report", reportIcon);
        btnLogout = new JButton ("Logout", logoutIcon); 
        btnBudget.setToolTipText("View or set your budget parameters");
        btnIncome.setToolTipText("View or edit your income details");
        btnExpenses.setToolTipText("View or enter your expenses");
        btnReport.setToolTipText("Set and generate a report");
        btnLogout.setToolTipText("End your session");
    }   
        

}
