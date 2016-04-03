/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.util.Observable;

/**
 *
 * @author Admin
 */
public class ReportSelectionView extends javax.swing.JPanel {

    /**
     * Creates new form ReportSelectionView2
     */
    public ReportSelectionView() {
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

        jPanel1 = new javax.swing.JPanel();
        btnDisplayReport = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        lblGenerateReport = new javax.swing.JLabel();
        lblMonth = new javax.swing.JLabel();
        comboMonth = new javax.swing.JComboBox();

        btnDisplayReport.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnDisplayReport.setText("Display Report");

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnClear.setText("Clear");

        lblGenerateReport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGenerateReport.setForeground(new java.awt.Color(255, 51, 255));
        lblGenerateReport.setText("Generate a Financial Report");

        lblMonth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMonth.setForeground(new java.awt.Color(255, 51, 255));
        lblMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/monthIcon.png"))); // NOI18N
        lblMonth.setText("    Select a Month");
        lblMonth.setToolTipText("");

        comboMonth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "View All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "November", "December" }));
        comboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(btnDisplayReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(lblMonth))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblGenerateReport)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGenerateReport)
                .addGap(86, 86, 86)
                .addComponent(lblMonth)
                .addGap(18, 18, 18)
                .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 248, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnDisplayReport))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMonthActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDisplayReport;
    private javax.swing.JComboBox comboMonth;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGenerateReport;
    private javax.swing.JLabel lblMonth;
    // End of variables declaration//GEN-END:variables
    
    
    public void clearValues() {
        comboMonth.setSelectedItem("View All"); 
    }
    
    public void displayDisplayReportView(double[] incomeData, String[] incomeLabels, double[] budgetData, String[] budgetLabels, double[] expenseData, String[] expenseLabels) {
        view.DisplayReportView displayReportPopup = new view.DisplayReportView(incomeData, incomeLabels, budgetData, budgetLabels, expenseData, expenseLabels); 
    }
    
    
    public String getMonth() {
        String month = comboMonth.getSelectedItem().toString();
        return month;
    }
    
    
    public String getShortMonth(String month){
        String shortMonth="";
        switch(month){
            case "January":
                shortMonth = "Jan";
                break;
            case "February":
                shortMonth = "Feb";
                break;
            case "March":
                shortMonth = "Mar";
                break;
            case "April":
                shortMonth = "Apr";
                break;
            case "May":
                shortMonth = "May";
                break;
            case "June":
                shortMonth ="Jun";
                break;
            case "July":
                shortMonth = "Jul";
                break;
            case "August":
                shortMonth = "Aug";
                break;
            case "September":
                shortMonth = "Sep";
                break;
            case "October":
                shortMonth = "Oct";
                break;
            case "November":
                shortMonth = "Nov";
                break;
            case "December":
                shortMonth = "Dec";
                break;
            default:
                shortMonth = "View All";
                break;
        }
        return shortMonth;
    }

    
    
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Used for interacting with model
    }

    
    public void addController(ActionListener controller){
		btnDisplayReport.addActionListener(controller);	//need instance of controller before can add it as a listener 
                btnDisplayReport.setActionCommand("DisplayReport"); 
                
                btnClear.addActionListener(controller);
                btnClear.setActionCommand("Clear"); 
	} 


}
