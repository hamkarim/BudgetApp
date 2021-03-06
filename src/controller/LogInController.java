
package controller;

import java.awt.event.ActionEvent;


public class LogInController implements java.awt.event.ActionListener {
    
    view.LogInView view; 
    model.UserStatus model; 
    
    public void actionPerformed(java.awt.event.ActionEvent e){
       
        if (e.getActionCommand() == "btnClear") {
            view.clearValues(); 
        }
        
        else if (e.getActionCommand() == "btnRegister") {
            
            if (view.getUsernameInput().isEmpty()) {
                view.displayMessage("emptyUsername");
            }
            else if (view.getPasswordInput().isEmpty()) {
                view.displayMessage("emptyPassword");
            }
            else if (model.searchUsername(view.getUsernameInput())) {
                view.displayMessage("nameTaken"); 
            }
            else {
                model.addUserEntry(view.getUsernameInput(), view.getPasswordInput()); 
                view.displayMessage("successfulRegister"); 
                view.clearValues(); 
            }
        }
        else if (e.getActionCommand() == "btnLogin") {
            if (view.getUsernameInput().isEmpty()) {
                view.displayMessage("emptyUsername");
            }
            else if (view.getPasswordInput().isEmpty()) {
                view.displayMessage("emptyPassword");
            }
            else if (model.searchUsername(view.getUsernameInput()) == false){
                view.displayMessage("userNotFound"); 
            }
            else if (model.attemptLogin(view.getUsernameInput(), view.getPasswordInput())) {
                view.logInSuccess();
            }
            else {
                view.displayMessage("invalidPassword");
            }

        }
    }

    public void addModel(model.UserStatus m){
            this.model = m;
    } 


    public void addView(view.LogInView v){
            this.view = v;
    } 


    
}


    

