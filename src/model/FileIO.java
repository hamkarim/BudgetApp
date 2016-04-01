
package model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.RandomAccessFile;
import java.sql.ResultSet;


public class FileIO {
    
    /*public static ArrayList<ExpenseEntry> readExpenses() {
        ArrayList<ExpenseEntry> expenseEntries = new ArrayList<>();
        int expenseID,day,month,year;
        String userID,category,description;
        double value;
        try {
            DataInputStream allExpense = new DataInputStream(new FileInputStream("Expenses.dat"));
            while(allExpense.available()>0)
            {
                userID = allExpense.readUTF();
                expenseID = allExpense.readInt();
                day = allExpense.readInt();
                month = allExpense.readInt();
                year = allExpense.readInt();
                category = allExpense.readUTF();
                description = allExpense.readUTF();
                value = allExpense.readDouble(); 
                if(UserStatus.getCurrentUser().equals(userID)) {
                    expenseEntries.add(new ExpenseEntry(expenseID,userID,day,month,year,description,category,value));
                }
            }
            allExpense.close();          
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "File Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
        }
        return expenseEntries;
        
    }
    
    public static void writeExpense(String userId, int expenseId, int day, int month, int year, String description, String category, double value) {
        try {
            //Store expense information to field expenses
            DataOutputStream output = new DataOutputStream(new FileOutputStream("Expenses.dat",true));
            output.writeUTF(userId);
            output.writeInt(expenseId);
            output.writeInt(day);
            output.writeInt(month);
            output.writeInt(year);
            output.writeUTF(category);
            output.writeUTF(description);
            output.writeDouble(value);                            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"File Open",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Invalid data for value!",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }       
    }*/
    

     
    /*  replaced with readUsers in DB Class
    public static ArrayList<User> readUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String userID, password;
        try {
            DataInputStream allUsers = new DataInputStream(new FileInputStream("Users.dat"));
            while(allUsers.available()>0){
                userID = allUsers.readUTF();
                password = allUsers.readUTF();
                userList.add(new User(userID, password));
            }
            allUsers.close();          
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "File Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
        }

        return userList; 
        
    } */ 
    /*  replaced with writeUser in DB Class
    public static void writeUser(String userId, String password) {
        try {
            //Store expense information to field expenses
            DataOutputStream output = new DataOutputStream(new FileOutputStream("Users.dat",true));
            output.writeUTF(userId);
            output.writeUTF(password);                   
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"File Open",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Invalid data for value!",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }       
    } */ 
    
    public static void writeIncome(String userId, double salary, double investments, double tax, double bonus, double total) {
        try {
            //Store expense information to field expenses
            DataOutputStream output = new DataOutputStream(new FileOutputStream("Income.dat",true));
            
            output.writeUTF(userId);
            output.writeDouble(salary);
            output.writeDouble(investments);
            output.writeDouble(tax);
            output.writeDouble(bonus);
            output.writeDouble(total);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"File Open",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Invalid data for value!",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }       
    }
    
    
    public static ArrayList<IncomeEntry> readIncome() {
        ArrayList<IncomeEntry> incomeEntries = new ArrayList<>();
        String userID;
        double salary, investments, tax, bonus, total;
        try {
            DataInputStream allIncome = new DataInputStream(new FileInputStream("Income.dat"));
            while(allIncome.available()>0)
            {
                userID = allIncome.readUTF();
                salary = allIncome.readDouble();
                investments = allIncome.readDouble();
                tax = allIncome.readDouble();
                bonus = allIncome.readDouble();
                total = allIncome.readDouble();
                incomeEntries.add(new IncomeEntry(userID, salary, investments, tax, bonus));
            }
            allIncome.close();          
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "File Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
        }
        return incomeEntries;        
    }
    
    public static void saveReport(String incomeArg, String budgetArg, String expensesArg) {
        String reportHeader = String.format("FINANCIAL REPORT FOR USER: %s\r\n\r\n\r\n", UserStatus.getCurrentUser()); 
        String incomeEntryString = incomeArg;
        String budgetEntryString = budgetArg;
        String expensesEntryString = expensesArg;

        FileWriter writer = null;
            try {
                writer = new FileWriter(UserStatus.getCurrentUser() + "-report.txt");
                    writer.write(reportHeader); 
                    writer.write(incomeEntryString);
                    writer.write(budgetEntryString); 
                    writer.write(expensesEntryString); 
                    writer.close();
                    String message = String.format("Report has been saved successfully.\nFile name: %s-report.txt", UserStatus.getCurrentUser()); 
                    JOptionPane.showMessageDialog(null, message, "Report Save Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "IO Exception", "Error", JOptionPane.ERROR_MESSAGE);
            }

    }
    

                            
    public static void writeBudget(String userId, double rent, double groceries, double clothing, double transportation, double education, double entertainment, double savings, double other) {
        try {
            //Store expense information to field expenses
            DataOutputStream output = new DataOutputStream(new FileOutputStream("Budget.dat",true));
            output.writeUTF(userId);
            output.writeDouble(rent);
            output.writeDouble(groceries);
            output.writeDouble(clothing);
            output.writeDouble(transportation);
            output.writeDouble(education);
            output.writeDouble(entertainment);
            output.writeDouble(savings);
            output.writeDouble(other);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"File Open",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Invalid data for value!",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }       
    }
    
    
    public static ArrayList<BudgetEntry> readBudget() {
        ArrayList<BudgetEntry> budgetEntries = new ArrayList<>();
        String userID;
        double rent, groceries, clothing, transportation, education, entertainment, savings, other;
        try {
            DataInputStream allBudget = new DataInputStream(new FileInputStream("Budget.dat"));
            while(allBudget.available()>0)
            {
                userID = allBudget.readUTF();
                rent = allBudget.readDouble();
                groceries = allBudget.readDouble();
                clothing = allBudget.readDouble();
                transportation = allBudget.readDouble();
                education = allBudget.readDouble();
                entertainment = allBudget.readDouble();
                savings = allBudget.readDouble();
                other = allBudget.readDouble();
                budgetEntries.add(new BudgetEntry(userID, rent, groceries, clothing, transportation, education, entertainment, savings, other));
            }
            allBudget.close();          
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "File Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
        }
        return budgetEntries;        
    }
    
        // Read a record from the specified RandomAccessFile
    public static ArrayList<ExpenseEntry> readExpenses(){
        ArrayList<ExpenseEntry> expenseEntries = new ArrayList<>();
        int expenseID,day,month,year,length=0;
        String userID,category,description;
        double value;
        byte[] buf = new byte[63];
        try {
            RandomAccessFile file = new RandomAccessFile("Expenses.dat","r");
            file.seek(0);        
            while(file.getFilePointer()<file.length()){
                length = file.read(buf);
                userID = new String(buf,0,length);
                expenseID = file.read();
                day = file.read();
                month = file.read();
                year = file.readInt();
                length = file.read(buf);
                category = new String(buf,0,length);
                length = file.read(buf);
                description = new String(buf,0,length);
                value = file.readDouble();
                if(UserStatus.getCurrentUser().equals(userID.trim())) {
                    expenseEntries.add(new ExpenseEntry(expenseID,userID.trim(),day,month,year,description.trim(),category.trim(),value));
                }
            }//while(file.getFilePointer()<file.length());
            file.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "File Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
        }
       return expenseEntries;
    }       
    
    public static void writeExpense(String userId, int expenseId, int day, int month, int year, String description, String category, double value) {
        try {//Store expense information to field expenses
            RandomAccessFile output = new RandomAccessFile("Expenses.dat","rw");
            userId = fulfillWhiteSpace(userId);
            description = fulfillWhiteSpace(description);
            category = fulfillWhiteSpace(category);
            output.seek(output.length());
            output.write(userId.getBytes());
            output.write(expenseId);
            output.write(day);
            output.write(month);
            output.writeInt(year);
            output.write(category.getBytes());
            output.write(description.getBytes());
            output.writeDouble(value);
            output.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"File Open",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Invalid data for value!",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }       
    }  
    
    
    public static void deleteExpense(String userId, int expenseId, int day, int month, int year, String description, String category, double value) {
        ArrayList<ExpenseEntry> allExpenses = readExpenses();
        for(int i=0;i<allExpenses.size();i++){
            if(allExpenses.get(i).getExpenseID()==expenseId&&allExpenses.get(i).getUserid().equals(userId)
                    &&allExpenses.get(i).getDay()==day&&allExpenses.get(i).getMonth()==month
                    &&allExpenses.get(i).getYear()==year&&allExpenses.get(i).getDescription().equals(description)
                    &&allExpenses.get(i).getCategory().equals(category)&&allExpenses.get(i).getValue()==value)
                allExpenses.remove(i);
        }    
        File originalFileName = new File("Expenses.dat");
        originalFileName.delete();       
        try {//Store expense information to field expenses
            RandomAccessFile rewriteFile= new RandomAccessFile("Expenses.dat" , "rw");
            for(int i=0;i<allExpenses.size();i++)
            {
                rewriteFile.write(fulfillWhiteSpace(allExpenses.get(i).getUserid()).getBytes());
                rewriteFile.write(allExpenses.get(i).getExpenseID());
                rewriteFile.write(allExpenses.get(i).getDay());
                rewriteFile.write(allExpenses.get(i).getMonth());
                rewriteFile.writeInt(allExpenses.get(i).getYear());
                rewriteFile.write(fulfillWhiteSpace(allExpenses.get(i).getCategory()).getBytes());
                rewriteFile.write(fulfillWhiteSpace(allExpenses.get(i).getDescription()).getBytes());
                rewriteFile.writeDouble(allExpenses.get(i).getValue());
            }
            rewriteFile.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"File Open",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Invalid data for value!",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }            
    }
    
    public static String fulfillWhiteSpace(String str){
        if(str.length()>63){
            str=str.substring(0,63);// 
        }else{
            while(str.length()<63){
                str+='\u0000';}//fulfill with whitespaces
        }
        return str;
    }
}

    

    

