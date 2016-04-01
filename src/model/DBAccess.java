
package model;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DBAccess {
    private static DBAccess database; 
    private static final String databaseDriver = "oracle.jdbc.driver.OracleDriver"; 
    final String HOSTINFO = "jdbc:oracle:thin:@dilbert.humber.ca:1521:grok"; 
    private Connection connection;
    private Statement statement;
    private static ResultSet userSet;
    
    
    private DBAccess() {}; 
    
    public static DBAccess getInstance() {
        if (database == null) {
            database = new DBAccess(); 
        }
        return database; 
    }
    
    public void establishConnection() throws Exception {
        // Step 1: Loading Driver
        Class.forName(databaseDriver); 
        // Step 2: Creating Connection
        connection = DriverManager.getConnection(HOSTINFO, "N00469373", "oracle");
        System.out.println("Database connection established: " + HOSTINFO);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
    }
    
    public void loadTables() throws Exception {
        this.getUsers();
    }
    
    
    public void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Database connection closed."); 
    }
    
    public void getUsers() throws SQLException, Exception {
        String sqlStm = "Select username, password from users"; 
        userSet = statement.executeQuery(sqlStm);
    }
    
    public static ArrayList<User> readUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String userID, password; 
        try { 
            if (userSet.first()) {
            //userSet.first(); 
                do {
                    userID = userSet.getString(1); 
                    password = userSet.getString(2);
                    userList.add(new User(userID, password));
                } while (userSet.next()); 
            }
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error loading user list from database. Program terminated.", "Error Loading Users", JOptionPane.ERROR_MESSAGE); 
            System.exit(1); 
        }
        return userList; 
    }
     
     public static void writeUser(String userId, String password) {
        try {
            userSet.moveToInsertRow(); 
            userSet.updateString("USERNAME", userId); 
            userSet.updateString("PASSWORD", password); 
            userSet.insertRow();                   
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error writing user to database. Program terminated.", "Error Writing User Data", JOptionPane.ERROR_MESSAGE);
            System.exit(1); 
        }
    }
    
        
    /*  Another possible way of accessing user information. However, this does not retrieve all users in the database, which is neccesary for checking if 
    // a username is available or already taken. Perhaps it just overcomplicates things unnecesarily when we can simply retrieve all the information from the database at once
    // for later usage from resultset/ array list storage
    public static void writeUser(String userid, String password) {
        try {
            String sql = "INSERT INTO USERS VALUES" + "(?,?)";
            PreparedStatement insertUser = connection.prepareStatement(sql);
            insertUser.setString(1, userid);
            insertUser.setString(2, password);
            insertUser.executeUpdate();
        } catch (SQLException s) { 
            JOptionPane.showMessageDialog(null, "Error writing user to database. Program terminated.", "Error Writing User Data", JOptionPane.ERROR_MESSAGE);
            System.exit(1); 
        }
    } */ 
  
}

