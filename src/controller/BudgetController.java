
package controller;

import java.awt.event.ActionEvent;
import model.DBAccess;
import model.IncomeStatus;


public class BudgetController implements java.awt.event.ActionListener {
    
    view.BudgetView view; 
    model.BudgetStatus model; 


    public void addModel(model.BudgetStatus m){
            this.model = m; 
    } 


    public void addView(view.BudgetView v){
            this.view = v;
    } 


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnClear"){                   
            view.clearValues();
        }
        else if (e.getActionCommand() == "btnUpdate") {
            if (view.getTotalPer() != 100.0) {
                view.showMessage("incorrectTotalPer"); 
            }
            else {
                model.updateBudgetEntry(view.getRentPercent(), view.getGroceryPercent(), view.getClothingPercent(),
                        view.getTransportationPercent(), view.getEducationPercent(), view.getEntertainmentPercent(), view.getSavingsPercent(), view.getOtherPercent()); 
                view.showMessage("budgetEntrySuccessful");
                this.loadBudget();  
            }
        }
    }

    public void loadBudget() {
        setBudgetPercentages(); 
        setBudgetValues(); 
    }
    
    public void setBudgetPercentages() {
        //DBAccess.readBudget();
        model.getBudgetData();
        view.setBudgetPercentages(model.getRentPercent(), model.getGroceryPercent(), model.getClothingPercent(),
        model.getTransportationPercent(), model.getEducationPercent(), model.getEntertainmentPercent(), model.getSavingsPercent(), model.getOtherPercent()); 
        
    }
    
    public void setBudgetValues() {
        IncomeStatus income = IncomeStatus.getInstance(); 
        income.retrieveValues(); 
        double totalIncome = IncomeStatus.getTotal();
        view.setValues(totalIncome, model.getRentPercent(), model.getGroceryPercent(), model.getClothingPercent(),
                model.getTransportationPercent(), model.getEducationPercent(), model.getEntertainmentPercent(), model.getSavingsPercent(), model.getOtherPercent());
        }
    
    
}
