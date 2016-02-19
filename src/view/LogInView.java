
package view;
import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import model.UserStatus;


public class LogInView extends JPanel implements java.util.Observer {
    
   
    private JButton btnLogin, btnClear, btnRegister;
    private JPanel panelButtonsSouth, panelDetailsCenter, panelWest, panelEast, panelLoggedInCenter; 
    private JTextField textUsername;
    private JPasswordField passPassword;
    private JLabel GIF,lblUsername, lblPassword, lblEast, lblWest, spacer1, spacer2, lblspacer1, lblspacer2, lblspacer3, lblLoggedIn;
    
    //icons are from easyicon.net
    private ImageIcon userIcon = new ImageIcon("images/userIcon.png");
    private ImageIcon passwordIcon = new ImageIcon("images/passwordIcon.png");
    private ImageIcon calculator = new ImageIcon("images/calculator.gif");
    
    Color loginOrange; 
    
    //protected static ArrayList<User> model.UserStatus.userList = new ArrayList<User>(); 
    
    
    public LogInView(){
        this.setLayout(new BorderLayout());
        this.initComponents();
        this.add(panelDetailsCenter,BorderLayout.CENTER);
        this.add(panelButtonsSouth,BorderLayout.SOUTH);
        this.add(panelEast, BorderLayout.EAST);
        this.add(panelWest, BorderLayout.WEST); 
    }
    
    

    private void initComponents(){
        
        GIF = new JLabel();
        GIF.setIcon(calculator);
        GIF.setLocation(400, 300);
        
        btnLogin = new JButton ("Login");
        btnClear = new JButton ("Clear");
        btnRegister = new JButton ("Register");
        textUsername = new JTextField();
        passPassword = new JPasswordField();
        
        textUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        passPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        
        lblUsername = new JLabel("Username");
        
        spacer1 = new JLabel(" ");
        spacer2 = new JLabel(" ");
        
        loginOrange = new Color(255,193,7); 
        lblUsername.setForeground(loginOrange);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setIcon(userIcon);
        
        lblPassword = new JLabel("   Password");
        
        lblPassword.setForeground(loginOrange);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setIcon(passwordIcon);
        
        lblEast = new JLabel("                             ");
        lblWest = new JLabel(" "); 
        
        panelDetailsCenter = new JPanel();
        panelDetailsCenter.setLayout(new GridLayout(4,2,10,55));
        
        panelDetailsCenter.add(spacer1);
        panelDetailsCenter.add(spacer2);
        
        panelDetailsCenter.add(lblUsername);
        panelDetailsCenter.add(textUsername);
        panelDetailsCenter.add(lblPassword);
        panelDetailsCenter.add(passPassword);
        
        panelLoggedInCenter = new JPanel();
        panelLoggedInCenter.setLayout(new GridLayout(4, 1)); 
        lblspacer1 = new JLabel(""); 
        lblspacer2 = new JLabel(""); 
        lblspacer3 = new JLabel(""); 
        lblLoggedIn = new JLabel(); 
        lblLoggedIn.setForeground(loginOrange);
        lblLoggedIn.setFont(new Font("Arial", Font.BOLD, 35));
        lblLoggedIn.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoggedIn.setText(""); 
        panelLoggedInCenter.add(lblspacer1);
        panelLoggedInCenter.add(lblspacer1);
        panelLoggedInCenter.add(lblLoggedIn); 
        panelLoggedInCenter.add(lblspacer3);
        
        panelButtonsSouth = new JPanel();
        panelButtonsSouth.add(btnLogin);
        panelButtonsSouth.add(btnClear);
        panelButtonsSouth.add(btnRegister);
        
        panelWest = new JPanel();
        panelWest.setLayout(new FlowLayout());
        panelWest.add(lblWest);
        
        panelEast = new JPanel();
        panelEast.setLayout(new FlowLayout());
        panelEast.add(lblEast);
        

    }
    
    //public static void addUserEntry(String userid, String password) {
    //    model.UserStatus.userList.add(new User(userid,password)); 
    //}
    // Clears fields on login screen
       
    public void clearValues() {
        textUsername.setText(""); 
        passPassword.setText("");
    }
    

    // Changes display to welcome message after user logs in
    public void loggedInScreen() {
        this.remove(panelDetailsCenter); 
        this.remove(panelButtonsSouth); 
        this.remove(panelEast);
        this.remove(panelWest);
        this.add(panelLoggedInCenter,BorderLayout.CENTER);
        lblLoggedIn.setText("   Welcome, " + model.UserStatus.getCurrentUser() + "!"); 
        lblLoggedIn.setIcon(calculator);
        this.revalidate();
        this.repaint(); 
    }
    
    // Resets screen to login screen after user logs out
    public void resetLoginScreen() {
        this.remove(panelLoggedInCenter); 
        this.add(panelDetailsCenter,BorderLayout.CENTER);
        this.add(panelButtonsSouth,BorderLayout.SOUTH);
        this.add(panelEast, BorderLayout.EAST);
        this.add(panelWest, BorderLayout.WEST); 
        this.revalidate();
        this.repaint(); 
        clearValues(); 
    }
    
    public String getUsernameInput() {
        return textUsername.getText() + ""; 
    }
            
    public String getPasswordInput() {
        String password = new String(passPassword.getPassword()); 
        return password + ""; 
    }
                    
    public void displayMessage(String messageType) {
        if (messageType == "emptyUsername") {
            JOptionPane.showMessageDialog(null, "Error - Please enter a username.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if (messageType == "emptyPassword") {
            JOptionPane.showMessageDialog(null, "Error - please enter a password.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if (messageType == "nameTaken") {
            JOptionPane.showMessageDialog(null, "Error - Username " + textUsername.getText() + " is already taken. Please choose a different username.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if (messageType == "userNotFound") {
            JOptionPane.showMessageDialog(null, "Error - Username " + textUsername.getText() + " does not exist. Please register if you have not yet made an account.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if (messageType == "successfulRegister") {
            JOptionPane.showMessageDialog(null, "User: " + textUsername.getText() + " was registered successfully. Please re-enter your login details and sign in to the system.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (messageType == "successfulLogin") {
            JOptionPane.showMessageDialog(null, "Login successful! Use the navigation buttons to view and edit your financial information.", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (messageType == "invalidPassword") {
            JOptionPane.showMessageDialog(null, "Error - invalid password.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public void logInSuccess() {
            displayMessage("successfulLogin"); 
            loggedInScreen(); 
        }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    public void addController(ActionListener controller){
        // Adding event handlers to the menu and nav bar items
        btnClear.addActionListener(controller);	
        btnClear.setActionCommand("btnClear"); 

        btnRegister.addActionListener(controller);	
        btnRegister.setActionCommand("btnRegister"); 

        btnLogin.addActionListener(controller);
        btnLogin.setActionCommand("btnLogin"); 
                
    } 
}
                
        
     