/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.*; 
/**
 *
 * @author Admin
 */
public class LogInView extends javax.swing.JPanel {

    /**
     * Creates new form LogInView2
     */
    public LogInView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        passPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        lblLoggedIn = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        lblUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUsername.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userIcon.png"))); // NOI18N
        lblUsername.setText("Username:");

        lblPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/passwordIcon.png"))); // NOI18N
        lblPassword.setText("   Password:");

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogin.setText("Login");

        btnClear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnClear.setText("Clear");

        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegister.setText("Register");

        lblLoggedIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLoggedIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calculator.gif"))); // NOI18N
        lblLoggedIn.setText("    Welcome, username!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 190, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(162, 162, 162)
                            .addComponent(passPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lblLoggedIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(182, 182, 182))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoggedIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRegister)
                        .addComponent(btnClear)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        lblLoggedIn.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    public void clearValues() {
        textUsername.setText(""); 
        passPassword.setText("");
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
    
    public void loggedInScreen() {
        btnClear.setVisible(false); 
        btnLogin.setVisible(false); 
        btnRegister.setVisible(false);
        lblPassword.setVisible(false);
        textUsername.setVisible(false); 
        passPassword.setVisible(false); 
        lblPassword.setVisible(false); 
        lblUsername.setVisible(false); 
        lblLoggedIn.setText("    Welcome, " + model.UserStatus.getCurrentUser() + "!"); 
        lblLoggedIn.setVisible(true); 
        this.revalidate();
        this.repaint(); 
    }
    
    public void resetLoginScreen() {
        btnClear.setVisible(true); 
        btnLogin.setVisible(true); 
        btnRegister.setVisible(true);
        lblPassword.setVisible(true);
        textUsername.setVisible(true); 
        passPassword.setVisible(true); 
        lblPassword.setVisible(true); 
        lblUsername.setVisible(true); 
        lblLoggedIn.setVisible(false); 
        this.revalidate();
        this.repaint(); 
        clearValues(); 
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblLoggedIn;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField passPassword;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}