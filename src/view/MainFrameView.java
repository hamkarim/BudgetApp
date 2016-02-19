
package view;

import com.sun.glass.events.KeyEvent;
import controller.IncomeController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import javax.swing.*;
import model.BudgetStatus;
import model.ExpenseStatus;
import model.IncomeStatus;
import model.UserStatus;

public class MainFrameView extends JFrame implements java.util.Observer {
    
    // Menu
    private JMenu fileMenu, helpMenu, categoryMenu, expensesMenu;
    private JMenuItem howMenuItem, aboutMenuItem, incomeMenuItem, budgetMenuItem, viewExpensesMenuItem, addExpenseMenuItem,reportMenuItem, logOutMenuItem, saveMenuItem, printMenuItem, exitMenuItem;
    private JMenuBar mb;
    private ImageIcon backgroundPic;
    private JLabel date,time;
   
    CardLayout cl;
    
    // Panels 
    private ExpensesView panelExpenses;
    private IncomeView panelIncome;
    private LogInView panelLogIn;
    private NavBarView panelNavBar;
    private ReportSelectionView panelReport;
    private BudgetView panelBudget;
    private JPanel cards; //a panel that uses CardLayout
    static String strExpenses = "Expenses";
    String strIncome = "Income";
    final static String strLogIn = "Log In";
    final static String strReport = "Report";
    final static String strBudget = "Budget";
    private String title = "Budget Application";
    
    public MainFrameView(){
        this.initComponents();
        this.setJMenuBar(mb); 
        this.add(panelNavBar,BorderLayout.NORTH);
        this.add(cards,BorderLayout.CENTER);
        this.setTitle(title);
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);       
    }
    
    public void initComponents()
    {
        // Menu items
        date =new JLabel();
        time =new JLabel();     
        showTime();
                
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");
        categoryMenu = new JMenu ("Category");
        expensesMenu = new JMenu ("Expenses");
        
        fileMenu.setMnemonic('F');
        helpMenu.setMnemonic('H');
        categoryMenu.setMnemonic('C');
        expensesMenu.setMnemonic('E'); 
        
        aboutMenuItem = new JMenuItem("About");
        howMenuItem = new JMenuItem("How to Use");
        budgetMenuItem = new JMenuItem("Budget");
        incomeMenuItem = new JMenuItem("Income");
        viewExpensesMenuItem = new JMenuItem("View");
        addExpenseMenuItem = new JMenuItem("Add"); 
        reportMenuItem = new JMenuItem("Report");
        logOutMenuItem = new JMenuItem("Log Out");
        saveMenuItem = new JMenuItem("Save");
        //printMenuItem = new JMenuItem("Print");
        exitMenuItem = new JMenuItem ("Exit");
        
        aboutMenuItem.setMnemonic('A');
        howMenuItem.setMnemonic('U');
        budgetMenuItem.setMnemonic('B');
        incomeMenuItem.setMnemonic('I');
        viewExpensesMenuItem.setMnemonic('V');
        addExpenseMenuItem.setMnemonic('D');
        reportMenuItem.setMnemonic('R');
        logOutMenuItem.setMnemonic('L');
        saveMenuItem.setMnemonic('S');
        //printMenuItem.setMnemonic('P');
        exitMenuItem.setMnemonic('X');
        
       //adding the sub menus
        fileMenu.add(logOutMenuItem);
        fileMenu.addSeparator(); 
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator(); 
        fileMenu.add(exitMenuItem);
        
        categoryMenu.add(budgetMenuItem);
        categoryMenu.addSeparator();
        categoryMenu.add(incomeMenuItem);
        categoryMenu.addSeparator();
        categoryMenu.add(expensesMenu);
        categoryMenu.addSeparator();
        categoryMenu.add(reportMenuItem);
        
        expensesMenu.add(viewExpensesMenuItem);
        expensesMenu.add(addExpenseMenuItem);

        helpMenu.add(howMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutMenuItem);
        helpMenu.addSeparator(); //lines separating menu items like groupings of sub menu items
        helpMenu.add(new JMenuItem("Version 1.0"));
        
        // shortcut keys
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        howMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        incomeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        budgetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        addExpenseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        viewExpensesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        reportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        logOutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));


        mb = new JMenuBar();
        mb.add(fileMenu);
        mb.add(categoryMenu);
        mb.add(helpMenu);
        mb.add(date);
        mb.add(time);
        mb.setPreferredSize(new Dimension(100, 50));
        
        // Creates the panels which display each separate screen and applies the controllers to each view
        panelExpenses = new ExpensesView();
        panelIncome = new IncomeView();
        panelBudget = new BudgetView();
        panelLogIn = new LogInView();
        panelNavBar = new NavBarView();   
        panelReport = new ReportSelectionView();
        
        controller.IncomeController incomeController = new controller.IncomeController();
        incomeController.addView(panelIncome);
        panelIncome.addController(incomeController);
        IncomeStatus incomeModel = new IncomeStatus(); 
        incomeController.addModel(incomeModel); 
        
        controller.ExpensesController expensesController = new controller.ExpensesController();
        expensesController.addView(panelExpenses);
        panelExpenses.addController(expensesController);
        ExpenseStatus myModel = new ExpenseStatus(); 
        expensesController.addModel(myModel); 
        
        controller.LogInController loginController = new controller.LogInController();
        loginController.addView(panelLogIn);
        panelLogIn.addController(loginController);
        UserStatus userModel = new UserStatus(); 
        loginController.addModel(userModel); 
        
        controller.BudgetController budgetController = new controller.BudgetController();
        budgetController.addView(panelBudget);
        panelBudget.addController(budgetController);
        BudgetStatus budgetModel = new BudgetStatus(); 
        budgetController.addModel(budgetModel); 
        
        controller.ReportSelectionController reportController = new controller.ReportSelectionController();
        reportController.addView(panelReport);
        panelReport.addController(reportController);
        
  
        cards = new JPanel(new CardLayout());
        cards.add(panelLogIn,strLogIn);
        
         
        cards.add(panelExpenses, strExpenses);
        cards.add(panelIncome, strIncome);
        cards.add(panelReport, strReport); 
        cards.add(panelBudget,strBudget);
        
        
        // Listener activates if budget or income panel is viewed
        // Communicates to controller to update the fields for the given user

        panelIncome.addComponentListener ( new ComponentAdapter ()
    {
        public void componentShown ( ComponentEvent e )
        {
            incomeController.setIncomeValues();
        }
        public void componentHidden ( ComponentEvent e )
        {
        }
    } );  
        
        panelBudget.addComponentListener ( new ComponentAdapter ()
    {
        public void componentShown ( ComponentEvent e )
        {
            budgetController.loadBudget(); 
        }
        public void componentHidden ( ComponentEvent e )
        {
        }
    } );  
        
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addController(ActionListener controller){
                // Adding event handlers to the menu and nav bar items
		howMenuItem.addActionListener(controller);	
                howMenuItem.setActionCommand("howMenuItem"); 
                
                aboutMenuItem.addActionListener(controller);	
                aboutMenuItem.setActionCommand("aboutMenuItem"); 
                
                exitMenuItem.addActionListener(controller);
                exitMenuItem.setActionCommand("exitMenuItem"); 
                
                budgetMenuItem.addActionListener(controller);
                budgetMenuItem.setActionCommand("btnBudget"); 
                
                panelNavBar.btnBudget.addActionListener(controller);
                panelNavBar.btnBudget.setActionCommand("btnBudget"); 

                incomeMenuItem.addActionListener(controller);
                incomeMenuItem.setActionCommand("btnIncome"); 
                
                panelNavBar.btnIncome.addActionListener(controller);
                panelNavBar.btnIncome.setActionCommand("btnIncome"); 
                
                viewExpensesMenuItem.addActionListener(controller);
                viewExpensesMenuItem.setActionCommand("btnExpenses"); 
                
                panelNavBar.btnExpenses.addActionListener(controller);
                panelNavBar.btnExpenses.setActionCommand("btnExpenses"); 
                
                addExpenseMenuItem.addActionListener(controller);
                addExpenseMenuItem.setActionCommand("btnAddExpense"); 
                
                reportMenuItem.addActionListener(controller);
                reportMenuItem.setActionCommand("btnReport"); 
                
                panelNavBar.btnReport.addActionListener(controller);
                panelNavBar.btnReport.setActionCommand("btnReport"); 
                
                logOutMenuItem.addActionListener(controller);
                logOutMenuItem.setActionCommand("btnLogout"); 
                
                panelNavBar.btnLogout.addActionListener(controller);
                panelNavBar.btnLogout.setActionCommand("btnLogout"); 
                
                saveMenuItem.addActionListener(controller);
                saveMenuItem.setActionCommand("btnSave"); 

	} 

     ///////
        public void showTime(){
            
            
            Thread timer = new Thread()
            {public void run(){
                for(;;){
                    
            Calendar cal=new GregorianCalendar();
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            date.setText("                                    Date today is:   "+year+"/"+(month+1)+"/"+day+"          ");
            date.setFont(new Font("Arial", Font.BOLD, 15));
            date.setForeground(Color.darkGray);
            String sec, min, hour;
            if (cal.get(Calendar.MINUTE) < 10) {
                min = "0" + cal.get(Calendar.MINUTE); 
            }
            else {
                min = "" + cal.get(Calendar.MINUTE); 
            }
            if (cal.get(Calendar.HOUR) == 0) {
                hour = "12";
            }
            else if (cal.get(Calendar.HOUR) < 10) {
                hour = "0" + cal.get(Calendar.HOUR); 
            }
            else {
                hour = "" + cal.get(Calendar.HOUR); 
            }
            if (cal.get(Calendar.SECOND) < 10) {
                sec = "0" + cal.get(Calendar.SECOND); 
            }
            else {
                sec = "" + cal.get(Calendar.SECOND); 
            }

            time.setText("Time right now is: " + hour+":"+min+":"+sec); 
            time.setFont(new Font("Arial", Font.BOLD, 15));
            time.setForeground(Color.darkGray);
                            
                            try
                            {
                                sleep(1000);
                            }
                            catch(InterruptedException ex)
                            {
                                
                            }
                }
                }
            };
            timer.start();
            
        }
                
    
    public void displayHowMessage() {
        String howToUse = String.format(
        "To use the app, follow these simple steps:" + 
        "\n\n First, enter your desired username and password and click register, or log in with your previously created account." + 
        "\n\n To enter your financial details:" + 
        "\n\n1. Input your budget goals on the budget screen by entering the percentage you aim to spend on each category per month." +
        "\n2. Enter the details of your monthly income on the income screen." +
        "\n3. Enter the details of your expenses on the expenses screen using the add expense option.") + 
        "\n\n To view your financial information: " + 
        "\n\n1. View your saveMenuItemd budget goals on budget page." + 
        "\n2. View your income details on the income page." + 
        "\n3. View your expenses on the expenses page by clicking the view expenses button." + 
        "\n4. The report page will allow you to view a full report detailing how well you managed your income and expenses, and if you met your budget goals successfully.";
        JOptionPane.showMessageDialog(null, howToUse, "How to Use", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void displayAboutMessage() {
        String about = String.format(
       "Budget App - Version 1.0" +
       "\n\nTeam Name: Sicoman" +
       "\nGroup Members: Michael Corrales, Man Luo, Cole Siegel" + 
       "\n\nCourse: Database Programming Using Java"); 
       JOptionPane.showMessageDialog(null, about, "About", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void displayNotLoggedIn() {
        JOptionPane.showMessageDialog(null, "Error - Please log in to your account", "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    // Switch to the corresponding panel, change the frame title, and set the values for the relevant fields
    public void displayBudgetView() {
        title = strBudget;
        //panelBudget.setPercentages(); 
        //panelBudget.setValues(); 
        panelNavBar.btnBudget.setEnabled(false);
        panelNavBar.btnIncome.setEnabled(true);
        panelNavBar.btnExpenses.setEnabled(true);
        panelNavBar.btnReport.setEnabled(true);
        panelNavBar.btnLogout.setEnabled(true);
        cl = (CardLayout)(cards.getLayout());
        cl.show(cards, strBudget); 
        MainFrameView.this.setTitle(title);
    }
    
    public void displayIncomeView() {
        title = strIncome;
        //panelIncome.setValues();
        panelNavBar.btnBudget.setEnabled(true);
        panelNavBar.btnIncome.setEnabled(false);
        panelNavBar.btnExpenses.setEnabled(true);
        panelNavBar.btnReport.setEnabled(true);
        panelNavBar.btnLogout.setEnabled(true);
        cl = (CardLayout)(cards.getLayout());
        cl.show(cards, strIncome);
        MainFrameView.this.setTitle(title);
    }
    
    public void displayExpensesView() {
        title = strExpenses;
        panelNavBar.btnBudget.setEnabled(true);
        panelNavBar.btnIncome.setEnabled(true);
        panelNavBar.btnExpenses.setEnabled(false);
        panelNavBar.btnReport.setEnabled(true);
        panelNavBar.btnLogout.setEnabled(true);
        cl = (CardLayout)(cards.getLayout());
        cl.show(cards, strExpenses);
        MainFrameView.this.setTitle(title);
    }
    
    public void displayAddExpenseView() {
        AddExpenseView expensePopUp = new AddExpenseView(); 
    }
        
    public void displayReportView() {
        title = strReport;
        panelNavBar.btnBudget.setEnabled(true);
        panelNavBar.btnIncome.setEnabled(true);
        panelNavBar.btnExpenses.setEnabled(true);
        panelNavBar.btnReport.setEnabled(false);
        panelNavBar.btnLogout.setEnabled(true);
        cl = (CardLayout)(cards.getLayout());
        cl.show(cards, strReport);
        MainFrameView.this.setTitle(title);
    }
    
    // Changes current user to null, loggedIn boolean to false, clears values on the other pages and resets login screen from welcome message to username/password
    public void displayLogoutView() {
        panelIncome.clearValues();
        panelBudget.clearValues(); 
        panelExpenses.clearValues();
        panelReport.clearValues(); 
        panelLogIn.resetLoginScreen(); 
        title = strLogIn;
        panelNavBar.btnBudget.setEnabled(true);
        panelNavBar.btnIncome.setEnabled(true);
        panelNavBar.btnExpenses.setEnabled(true);
        panelNavBar.btnReport.setEnabled(true);
        panelNavBar.btnLogout.setEnabled(true);
        cl = (CardLayout)(cards.getLayout());
        cl.show(cards, strLogIn);
        MainFrameView.this.setTitle(title);
    }
}
    

 