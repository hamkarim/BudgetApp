
package controller;

import java.awt.event.ActionEvent;


public class AddExpenseController implements java.awt.event.ActionListener {
    
    view.AddExpenseView view; 
    model.ExpenseStatus model; 


    public void addModel(model.ExpenseStatus m){
            this.model = m; 
    } 


    public void addView(view.AddExpenseView v){
            this.view = v;
    } 

    /* 
    public void initModel(int x){
            model.setValue(x);
    } //initModel()
    */ 
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnClear"){ 
            System.out.println("clear clicked"); 
            view.clearValues();
        }
        else if (e.getActionCommand() == "btnAdd") {
            if (!view.isDateSelected()){
                view.displayMessage("noDate"); 
            }
            else if (view.getValue() == 0.0){
                view.displayMessage("noValue"); 
            }
            else {
                model.addExpenseEntry(view.getDay(),view.getMonth(),view.getYear(),view.getDescription(),view.getCategory(),view.getValue()); 
                view.displayMessage("expenseAdded"); 
            }
            
            
        }
            
    }
}
    
