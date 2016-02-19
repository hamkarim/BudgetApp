
package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.IncomeStatus;


public class IncomeView extends JPanel implements java.util.Observer {
    
    private JLabel lblSalary,lblInvestments,lblBonus,lblTax,lblTotal, lblWest,lblEast, lblCategoryHeading, lblValueHeading; 
    private JSpinner spinSalary, spinInvestments, spinBonus, spinTax;
    private SpinnerNumberModel modelSalary, modelInvestments, modelBonus, modelTax;
    protected JTextField txtTotal; 
    private JButton btnUpdate,btnClear;
    private JPanel panelInfoArea,panelButtons, panelWest,panelEast;
    
    public IncomeView(){
        this.setLayout(new BorderLayout()); 
        this.initComponents();
        this.add(panelInfoArea,BorderLayout.CENTER);
        this.add(panelButtons,BorderLayout.SOUTH);
        this.add(panelWest,BorderLayout.WEST);
        this.add(panelEast,BorderLayout.EAST);
    }
    
    public void initComponents()
    {
        lblSalary = new JLabel("Salary");
        lblInvestments = new JLabel("Investments");
        lblBonus = new JLabel("Bonus");
        lblTax = new JLabel("Grants/Tax Credits");
        lblTotal = new JLabel("Total Income");
        lblWest = new JLabel(" ");
        lblEast = new JLabel(" ");
        
        lblTotal.setForeground(Color.red);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 20));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblCategoryHeading = new JLabel("Category of Earnings");
        lblCategoryHeading.setForeground(Color.red);
        lblCategoryHeading.setFont(new Font("Arial", Font.BOLD, 20));
        lblCategoryHeading.setHorizontalAlignment(SwingConstants.CENTER);
        lblValueHeading = new JLabel("Monthly Value"); 
        lblValueHeading.setForeground(Color.red);
        lblValueHeading.setFont(new Font("Arial", Font.BOLD, 20));
        lblValueHeading.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
        lblInvestments.setHorizontalAlignment(SwingConstants.CENTER);
        lblBonus.setHorizontalAlignment(SwingConstants.CENTER);
        lblTax.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblSalary.setFont(new Font("Arial", Font.BOLD, 20));
        lblInvestments.setFont(new Font("Arial", Font.BOLD, 20));
        lblBonus.setFont(new Font("Arial", Font.BOLD, 20));
        lblTax.setFont(new Font("Arial", Font.BOLD, 20));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 20));
        
        modelSalary = new SpinnerNumberModel(0.0, 0.0, 9999999999.99, 1.0); 
        modelInvestments = new SpinnerNumberModel(0.0, 0.0, 9999999999.99, 1.0); 
        modelBonus = new SpinnerNumberModel(0.0, 0.0, 9999999999.99, 1.0); 
        modelTax = new SpinnerNumberModel(0.0, 0.0, 9999999999.99, 1.0); 

        spinSalary = new JSpinner(modelSalary); 
        spinInvestments = new JSpinner(modelInvestments); 
        spinBonus = new JSpinner(modelBonus); 
        spinTax = new JSpinner(modelTax); 
        
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        
        btnUpdate = new JButton("Update");
        btnClear = new JButton("Clear");
                
        panelInfoArea = new JPanel();
        panelInfoArea.setLayout(new GridLayout(6,2));
        panelInfoArea.add(lblCategoryHeading);
        panelInfoArea.add(lblValueHeading);
        panelInfoArea.add(lblSalary);
        panelInfoArea.add(spinSalary);
        panelInfoArea.add(lblInvestments);
        panelInfoArea.add(spinInvestments);
        panelInfoArea.add(lblBonus);
        panelInfoArea.add(spinBonus);
        panelInfoArea.add(lblTax);
        panelInfoArea.add(spinTax);
        panelInfoArea.add(lblTotal);
        panelInfoArea.add(txtTotal);
        panelInfoArea.setBackground(Color.getHSBColor(5, 250, 127));
        
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(btnUpdate);
        panelButtons.add(btnClear);

        panelWest = new JPanel();
        panelWest.setLayout(new FlowLayout());
        panelWest.add(lblWest);
        
        panelEast = new JPanel();
        panelEast.setLayout(new FlowLayout());
        panelEast.add(lblEast);
        
        spinSalary.addChangeListener(new Changes());
        spinInvestments.addChangeListener(new Changes());
        spinBonus.addChangeListener(new Changes());
        spinTax.addChangeListener(new Changes());
       
        
                
    }

    
    
    public void addController(ActionListener controller){
        // Adding event handlers to the menu and nav bar items
        btnClear.addActionListener(controller);	
        btnClear.setActionCommand("btnClear"); 

        btnUpdate.addActionListener(controller);	
        btnUpdate.setActionCommand("btnUpdate");     
        
    } 
    
    public double getTotal() {
        double totalVal = (double)spinSalary.getValue() + (double)spinInvestments.getValue() + (double)spinBonus.getValue() + (double)spinTax.getValue(); 
        return totalVal; 
    }
    
    public double getSalary() {
        double salary = (Double)spinSalary.getValue();
        return salary;
    }
    
    public double getInvestments() {
        double investments = (Double)spinInvestments.getValue(); 
        return investments;
    }
    
    public double getTax() {
        double tax = (Double)spinTax.getValue();
        return tax;
        
    }
    
    public double getBonus() {
        double bonus = (Double)spinBonus.getValue();
        return bonus;
    }
    
    public void setTotal(double total) {
        String totalString = Double.toString(total); 
        txtTotal.setText(totalString); 
    }
    
    public void displayUpdateMessage() {
        JOptionPane.showMessageDialog(null, "Income updated successfully.", "Income Update Successful", JOptionPane.INFORMATION_MESSAGE);                    
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Allows for dynamic updating of value for total income 
    // Handler kept in view because updates are only concerned with display and do not affect the model layer
    private class Changes implements ChangeListener{
            double totalVal;
            public void stateChanged(ChangeEvent e) {
                if(e.getSource() == spinSalary){
                    totalVal = (double)spinSalary.getValue() + (double)spinInvestments.getValue() + (double)spinBonus.getValue() + (double)spinTax.getValue();
                    txtTotal.setText(totalVal+"");
                }else if(e.getSource() == spinInvestments){
                    totalVal = (double)spinSalary.getValue() + (double)spinInvestments.getValue() + (double)spinBonus.getValue() + (double)spinTax.getValue();
                    txtTotal.setText(totalVal+"");
                }else if(e.getSource() == spinBonus){
                    totalVal = (double)spinSalary.getValue() + (double)spinInvestments.getValue() + (double)spinBonus.getValue() + (double)spinTax.getValue();
                    txtTotal.setText(totalVal+"");
                }else if(e.getSource() == spinTax){
                    totalVal = (double)spinSalary.getValue() + (double)spinInvestments.getValue() + (double)spinBonus.getValue() + (double)spinTax.getValue();
                    txtTotal.setText(totalVal+"");
                }            
            }
        }
 
    // Fills in the fields based on user's information as soon as they navigate to the page
    public void setValues(double salary, double investments, double bonus, double tax, double total) {
        spinSalary.setValue(salary); 
        spinInvestments.setValue(investments); 
        spinBonus.setValue(bonus); 
        spinTax.setValue(tax); 
        txtTotal.setText(Double.toString(total)); 
    }
    
   
    public void clearValues() {
        spinSalary.setValue(0.0); 
        spinInvestments.setValue(0.0);
        spinBonus.setValue(0.0); 
        spinTax.setValue(0.0);
        txtTotal.setText(""); 
    }
     
     
}

