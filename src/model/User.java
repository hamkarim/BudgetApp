package model;




public class User {

    private String userid; 
    private String password; 


    public User(String userid, String password) {
        this.userid = userid; 
        this.password = password;  
    }
    
    public String getUserid() {
        return userid;
    }
    
    public String getPassword() {
        return password;
    }
    
        
}
