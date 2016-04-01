
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBAccess {
    private static DBAccess database; 
    private static final String databaseDriver = "oracle.jdbc.driver.OracleDriver"; 
    final String HOSTINFO = "jdbc:oracle:thin:@dilbert.humber.ca:1521:grok"; 
    private Connection connection;
    private Statement statement;
    private ResultSet rs = null; 
    
    
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
    
    
    
    /* new commands for modifying database tables
    public DataAccess(String userName, String password) throws Exception {
    // Step 1: Loading Driver
    Class.forName(databaseDriver); 

    // Step 2: Creating Connection
    connection = DriverManager.getConnection(HOSTINFO, userName, password);
    System.out.println("Database connection established: " + HOSTINFO);
    statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE); 
    } */ 
    /* 
    public void modifyData() throws SQLException {
        //PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO job VALUES(6, \"Hello\")", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE); 
        rs.moveToInsertRow();
        //rs.updateString("JOB_DESCRIPTION", "User's input ofnew description"); 
        //rs.updateRow(); 
    } */ 
    
    public void closeConnection() throws SQLException {
        connection.close();
        System.out.println("Database connection closed."); 
    }
    
    
    public ResultSet getUsers() throws SQLException, Exception {
        String sqlStm = "Select username, password from users"; 
        try {
            rs = statement.executeQuery(sqlStm);
        } finally {
            return rs;
        }
    }
        
     
}

