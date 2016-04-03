
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

    public void setIncomeValues() {
            model.retrieveValues(); 
            view.setValues(model.getSalary(), model.getInvestments(), model.getBonus(), model.getTax(), model.getTotal()); 
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnClear"){                   
            view.clearValues();
        }
        else if (e.getActionCommand() == "btnUpdate") {
                view.setTotal(view.getSalary() + view.getInvestments() + view.getTax() + view.getBonus());
                model.updateIncomeEntry(view.getSalary(), view.getInvestments(), view.getTax(), view.getBonus(), view.getTotal());
                view.displayUpdateMessage(); 
        }
            //view.setTotal(totalVal); 
   
    }
}
    
    

