
package controller;

import model.UserStatus;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import model.BudgetStatus;
import model.ExpenseStatus;
import model.FileIO;
import model.DBAccess; 
import model.IncomeStatus;


public class MainFrameController implements java.awt.event.ActionListener {
    

    view.MainFrameView view; 
    model.UserStatus model; 

    
    //invoked when a button is pressed
    public void actionPerformed(java.awt.event.ActionEvent e){
        
        // nav bar
        if (e.getActionCommand() == "howMenuItem") {
            view.displayHowMessage();
        }
        else if (e.getActionCommand() == "aboutMenuItem") {
            view.displayAboutMessage(); 
        }
        else if (e.getActionCommand() == "exitMenuItem") {
            DBAccess db = DBAccess.getInstance(); 
            try {
                DBAccess database = DBAccess.getInstance(); 
                database.closeConnection(); 
            } catch (SQLException s) {}; 

            System.exit(0); 
        }
        else if (UserStatus.getLoggedIn() == false) {
            view.displayNotLoggedIn(); 
        }
        else if (e.getActionCommand() == "btnBudget") {
            view.displayBudgetView(); 
        }
        else if (e.getActionCommand() == "btnIncome") {
            view.displayIncomeView(); 
        }
        else if (e.getActionCommand() == "btnExpenses") {
            view.displayExpensesView(); 
        }
        else if (e.getActionCommand() == "btnAddExpense") {
            view.displayAddExpenseView(); 
        }
        else if (e.getActionCommand() == "btnReport") {
            view.displayReportView(); 
        }
        else if (e.getActionCommand() == "btnLogout") {
            view.displayLogoutView(); 
            model.setLoggedIn(false); 
            model.clearCurrentUser();  
        }
        else if (e.getActionCommand() == "btnSave") {
            IncomeStatus income = IncomeStatus.getInstance(); 
            String incomeEntryString = income.getIncomeEntryString(); 
            BudgetStatus budget = BudgetStatus.getInstance(); 
            String budgetEntryString = budget.getBudgetEntryString(); 
            String expenseEntryString = ExpenseStatus.getExpensesString("View All", "View All"); 
            FileIO.saveReport(incomeEntryString, budgetEntryString, expenseEntryString); 
        } 
    }
    

 
    public void addModel(UserStatus m){
            this.model = m;
    } 
        
    public void addView(view.MainFrameView v){
            this.view = v;
    } 

    
}
