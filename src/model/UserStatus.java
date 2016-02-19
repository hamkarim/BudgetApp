
package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserStatus {
    private static ArrayList<User> userList = new ArrayList<User>(); 
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
        userList.add(new User(userid,password)); 
        FileIO.writeUser(userid, password);
    }

    public int searchUsername(String searchPattern) {
            userList = FileIO.readUsers();
            int userIndex; 
            for (int i = 0; i < userList.size(); i++) {
                    if  (userList.get(i).getUserid().equals(searchPattern)) {
                        userIndex = i; 
                        return userIndex; 
                    }
                }
            return -1; 
    }
      
    public boolean attemptLogin(String username, String password) {
            int userIndex = searchUsername(username); 
            if (userList.get(userIndex).getPassword().equals(password)) {
                this.setCurrentUser(username); 
                this.setLoggedIn(true); 
                return true;
            }
        
        return false; 
        
        
    }
        
                
               

}


