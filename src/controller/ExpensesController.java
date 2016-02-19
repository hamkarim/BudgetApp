
package controller;

import java.awt.event.ActionEvent;
import model.ExpenseStatus;


public class ExpensesController implements java.awt.event.ActionListener {
    
    view.ExpensesView view; 
    model.ExpenseStatus model; 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnDisplay") {
            Object[][] rowData = ExpenseStatus.expenseTableFormat(ExpenseStatus.filterExpenses(view.getCategory(), view.getMonth())); 
            if (rowData.length == 0) {
                view.displayNoExpenses(); 
            }
            else {
                view.displayExpenses(view.getCategory(),view.getMonth(),rowData); 
            }
        }
        else if (e.getActionCommand() == "btnAddExpense") {
            view.displayAddExpense(); 
        }
        else if (e.getActionCommand() == "btnClear") {
            view.clearValues(); 
        }
    }
    
    
    public void addModel(model.ExpenseStatus m){
            this.model = m;
    } 


    public void addView(view.ExpensesView v){
            this.view = v;
    }    

}