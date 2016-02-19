
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ExpenseStatus;


public class EditExpenseController implements ActionListener {
    
    view.EditExpenseView view; 
    model.ExpenseStatus model; 


    public void addModel(model.ExpenseStatus m){
            this.model = m; 
    } 

    public void addView(view.EditExpenseView v){
            this.view = v;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnDelete"){                   
            view.clearValues();
        }
        else if (e.getActionCommand() == "btnUpdate") {
            if (!view.isDateSelected()){
                view.displayMessage("noDate"); 
            }
            else if (view.getValue() == 0.0){
                view.displayMessage("noValue"); 
            }
            else {
                ExpenseStatus.updateExpenseEntry(view.getInitExpenseEntry(), view.getUpdatedExpenseEntry());
                view.displayMessage("expenseUpdated"); 
            }            
        }
            
    }
}
    
