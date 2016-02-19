/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package budgetapp;

import model.IncomeStatus;
import view.IncomeView;
import view.MainFrameView;

public class BudgetApp {

    public static void main(String[] args) {

        // Main frame contains CardLayout to change between various views
        view.MainFrameView window1 = new MainFrameView(); 
        controller.MainFrameController mainFrameController = new controller.MainFrameController();
        mainFrameController.addView(window1);
        window1.addController(mainFrameController);
        


    }
    
    
}
