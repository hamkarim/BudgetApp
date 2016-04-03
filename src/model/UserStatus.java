
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserStatus {
    private static boolean loggedIn = false;
    private static String currentUser = ""; 
    
    public static String getCurrentUser() {
        return currentUser;
    } 
    
    public static void setCurrentUser(String newUser) {
        currentUser = newUser; 
    }
    
    public static void clearCurrentUser() {
        currentUser = ""; 
    }

    public static void setLoggedIn(boolean trueOrFalse) {
        loggedIn = trueOrFalse;
    }

    public static boolean getLoggedIn() {
        return loggedIn;
    }
    
    public static void addUserEntry(String userid, String password) {
        DBAccess.writeUser(userid, password);
    }
    
    public boolean searchUsername(String searchPattern) {
            boolean userExists = DBAccess.readUser(searchPattern);
            return userExists;
    }
      
    public boolean attemptLogin(String username, String password) {            
            if (DBAccess.readUser(username, password)) {
                this.setCurrentUser(username); 
                this.setLoggedIn(true); 
                return true;
            } 
        
        return false; 
        
        
    }
        
                
               

}


