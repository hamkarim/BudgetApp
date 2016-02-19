/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;
import model.*;
/**
 *
 * @author Lynn
 */
public class DisplayExpensesController implements ActionListener{

    DisplayExpensesView view; 
    ExpenseStatus model; 

    public void addModel(ExpenseStatus m){
            this.model = m; 
    } 
    public void addView(DisplayExpensesView v){
            this.view = v;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(null != e.getActionCommand())switch (e.getActionCommand()) {
            case "btnEdit":
                view.displayEidtExpense(); //To change body of generated methods, choose Tools | Templates.     
                break;
            case "btnDelete":
                if(view.isItemSelected()){
                    ExpenseStatus.deleteExpenseEntry(view.getSelectedItem().getUserid(),view.getSelectedItem().getExpenseID(),view.getSelectedItem().getDay(),
                            view.getSelectedItem().getMonth(),view.getSelectedItem().getYear(),view.getSelectedItem().getDescription(),
                            view.getSelectedItem().getCategory(),view.getSelectedItem().getValue());
                    view.displayMessage("expenseDeleted");
                    view.refreshExpense(ExpenseStatus.expenseTableFormat(ExpenseStatus.filterExpenses(view.getCategory(), view.getMonth())));
                }
                else{
                    view.displayMessage("noSelectedItem");
            }   break;
        }        
    }    
}
