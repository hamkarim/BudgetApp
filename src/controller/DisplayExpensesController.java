
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.DisplayExpensesView;
import model.ExpenseStatus; 
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
                    model.deleteExpenseEntry(view.getSelectedItem().getExpenseID(),view.getSelectedItem().getUserid());
                    view.displayMessage("expenseDeleted");
                    view.refreshExpense(ExpenseStatus.expenseTableFormat(ExpenseStatus.filterExpenses(view.getCategory(), view.getMonth())));
                }
                else{
                    view.displayMessage("noSelectedItem");
            }   break;
        }        
    }    
}
