
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


public class FileIO {
    
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
                    expenseEntries.add(new ExpenseEntry(userID.trim(),expenseID,day,month,year,description.trim(),category.trim(),value));
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

    

    

