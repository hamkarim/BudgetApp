/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Lynn
 */
public class Consts {
    public enum Expense{
        EXPENSEID("expenseid"),
        DATEOFPURCHASE("dateofpurchase"),
        CATEGORY("category"),
        DESCRIPTION("description"),
        VALUE("value");
        private String value;
        private Expense(String value){
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
    }
    
    public enum User{
        USERNAME("username"),
        PASSWORD("password");
        
        private String value;
        private User(String value){
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
    }
    
    public enum ExpenseUser{
        EXPENSEID("expenseid"),
        USERNAME("username");
        
        private String value;
        
        private ExpenseUser(String value){
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
    }
}
