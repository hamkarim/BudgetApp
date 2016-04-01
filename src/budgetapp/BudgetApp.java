/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package budgetapp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DBAccess;
import view.MainFrameView;

public class BudgetApp {

    public static void main(String[] args) {

        try {
            //Establishing database connection
            DBAccess database = model.DBAccess.getInstance(); 
            database.establishConnection(); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error establishing database connection. Please check your internet connection. Program terminated.", "Error Connecting to Database", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }
        // Main frame contains CardLayout to change between various views
        view.MainFrameView window1 = new MainFrameView(); 
        controller.MainFrameController mainFrameController = new controller.MainFrameController();
        mainFrameController.addView(window1);
        window1.addController(mainFrameController);
        


    }
    
    
}
