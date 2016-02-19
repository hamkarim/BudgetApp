
package controller;

import java.awt.event.ActionEvent;
import model.FileIO;


public class IncomeController implements java.awt.event.ActionListener {
    
    view.IncomeView view; 
    model.IncomeStatus model; 


    public void addModel(model.IncomeStatus m){
            this.model = m; 
    } 


    public void addView(view.IncomeView v){
            this.view = v;
    } 

    /* 
    public void initModel(int x){
            model.setValue(x);
    } //initModel()
    */ 
    
    public void setIncomeValues() {
        int userIndex = model.searchIncomeEntry(); 
        if (userIndex >= 0) {
            view.setValues(model.getSalary(userIndex), model.getInvestments(userIndex), model.getBonus(userIndex), model.getTax(userIndex), model.getTotal(userIndex)); 
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnClear"){                   
            view.clearValues();
        }
        else if (e.getActionCommand() == "btnUpdate") {
            double totalVal = view.getTotal();
            int userIndex = model.searchIncomeEntry(); 
            if (userIndex >= 0) {
                model.updateIncomeEntry(userIndex, view.getSalary(), view.getInvestments(), view.getTax(), view.getBonus(), view.getTotal());
            }
            else if (userIndex == -1) {
                model.addIncomeEntry(view.getSalary(), view.getInvestments(), view.getTax(), view.getBonus());
            }
            view.setTotal(totalVal); 
            view.displayUpdateMessage(); 
        }
   
    
    }
}
    
    

