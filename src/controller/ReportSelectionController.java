
package controller;

import java.awt.event.ActionEvent;
import model.BudgetStatus;
import model.ExpenseStatus;
import model.IncomeStatus; 
import model.UserStatus;

public class ReportSelectionController implements java.awt.event.ActionListener{
     view.ReportSelectionView view; 

    public void addView(view.ReportSelectionView v){
		this.view = v;
	} 

    public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "DisplayReport") {
                IncomeStatus income = IncomeStatus.getInstance();
                double[] incomeData = income.getIncomeData();
                String[] incomeLabels = income.getIncomeLabels(); 
                BudgetStatus budget = BudgetStatus.getInstance(); 
                double[] budgetData = budget.getBudgetData(); 
                String[] budgetLabels = budget.getBudgetLabels();
                ExpenseStatus expenses = new ExpenseStatus(); 
                double[] expenseData = ExpenseStatus.getExpenseData(ExpenseStatus.filterExpenses("View All", view.getShortMonth(view.getMonth()))); 
                String[] expenseLabels = ExpenseStatus.getExpenseLabels(ExpenseStatus.filterExpenses("View All", view.getShortMonth(view.getMonth()))); 
                view.DisplayReportView displayReportPopup = new view.DisplayReportView(incomeData, incomeLabels, budgetData, budgetLabels, expenseData, expenseLabels);
                displayReportPopup.setTitle(UserStatus.getCurrentUser() + " - Financial Report"); 
                displayReportPopup.setHeading(UserStatus.getCurrentUser(), view.getMonth()); 
                String incomeString = income.getIncomeEntryString(); 
                String budgetString = budget.getBudgetEntryString(); 
                String expenseString = ExpenseStatus.getExpensesString("View All", view.getShortMonth(view.getMonth())); 
                
                displayReportPopup.setReports(incomeString, budgetString, expenseString); 
        }
            else if (e.getActionCommand() == "Clear") {
                view.clearValues(); 
            }
    }
    
    
    

     

}
