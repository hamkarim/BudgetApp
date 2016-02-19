
package controller;

import java.awt.event.ActionEvent;
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

    /* 
    public void initModel(int x){
            model.setValue(x);
    } //initModel()
    */ 
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnClear"){                   
            view.clearValues();
        }
        else if (e.getActionCommand() == "btnUpdate") {
            int userIndex = model.searchBudgetEntry(); 
            if (view.getTotalPer() != 100.0) {
                view.showMessage("incorrectTotalPer"); 
            }
            else {
                if (userIndex >= 0) {
                model.updateBudgetEntry(userIndex, view.getRentPercent(), view.getGroceryPercent(), view.getClothingPercent(),
                        view.getTransportationPercent(), view.getEducationPercent(), view.getEntertainmentPercent(), view.getSavingsPercent(), view.getOtherPercent()); 
                }
                else {
                model.addBudgetEntry(view.getRentPercent(), view.getGroceryPercent(), view.getClothingPercent(),
                        view.getTransportationPercent(), view.getEducationPercent(), view.getEntertainmentPercent(), view.getSavingsPercent(), view.getOtherPercent()); 
                }
                view.showMessage("budgetEntrySuccessful");
                loadBudget();  
            }
            
        }
    }

    public void loadBudget() {
        setBudgetPercentages(); 
        setBudgetValues(); 
    }
    
    public void setBudgetPercentages() {
        int userIndex = model.searchBudgetEntry(); 
        if (userIndex >= 0) {
            view.setBudgetPercentages(model.getRentPercent(userIndex), model.getGroceryPercent(userIndex), model.getClothingPercent(userIndex),
                model.getTransportationPercent(userIndex), model.getEducationPercent(userIndex), model.getEntertainmentPercent(userIndex), model.getSavingsPercent(userIndex), model.getOtherPercent(userIndex)); 
        }
    }
    
    public void setBudgetValues() {
        int userIndex = model.searchBudgetEntry(); 
        IncomeStatus income = new IncomeStatus();
        int incomeIndex = income.searchIncomeEntry(); 
        if (userIndex == -1 && incomeIndex == -1) {
            view.clearValues();
        }
        else if (userIndex >= 0 && incomeIndex >= 0) {
            double totalIncome = income.getTotal(incomeIndex);
            view.setValues(totalIncome, model.getRentPercent(userIndex), model.getGroceryPercent(userIndex), model.getClothingPercent(userIndex),
                model.getTransportationPercent(userIndex), model.getEducationPercent(userIndex), model.getEntertainmentPercent(userIndex), model.getSavingsPercent(userIndex), model.getOtherPercent(userIndex));
        }
        else if (userIndex >= 0) {
            double totalIncome = 0; 
            view.setValues(totalIncome, model.getRentPercent(userIndex), model.getGroceryPercent(userIndex), model.getClothingPercent(userIndex),
                model.getTransportationPercent(userIndex), model.getEducationPercent(userIndex), model.getEntertainmentPercent(userIndex), model.getSavingsPercent(userIndex), model.getOtherPercent(userIndex));
        }
        else if (incomeIndex >= 0) {
            double totalIncome = income.getTotal(incomeIndex);
            view.setValues(totalIncome, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, 0.0);
        }
    }
    
    
}
