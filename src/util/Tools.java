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
public class Tools {
    
    public static String getDate(int day, int month, int year){
        String strDay=""; 
        if (day < 10) {
            strDay = "0" + day;
        }else {
            strDay = "" + day; 
        }
        return strDay +"-"+getMonthString(month)+"-"+year;
    }
                
    public static int getDay(String date){
        int day=0;
        if(date.indexOf("-")==1)
        {
            day = Integer.parseInt(date.substring(0, 2));
        }else{
            day = Integer.parseInt(date.substring(8, 10));
        } 
        return day;
    }
    public static int getMonth(String date){
        int month = 0;
        if(date.indexOf("-")==1){
            date = date.substring(3, 6);
            month = getIntMonth(date);
        }else{
            month = Integer.parseInt(date.substring(5, 7));
        }
        return month;
    }
    public static int getYear(String date){
        int year = 0;
        if(date.indexOf("-")==1){
            year = Integer.parseInt(date.substring(7));
        }else{
            year = Integer.parseInt(date.substring(0, 4));
        }
        return year;
    }
    
    public static int getIntMonth(String monthString) {
        int month = 0;
        switch(monthString){
            case "Jan": 
                month=1;
                break;
            case "Feb":
                month=2;
                break;
            case "Mar":
                month=3;
                break;
            case "Apr":
                month=4;
                break;
            case "May":
                month=5;
                break;
            case "Jun":
                month=6;
                break;
            case "Jul":
                month=7;
                break;
            case "Aug":
                month=8;
                break;
            case "Sep":
                month=9;
                break;
            case "Oct":
                month=10;
                break;
            case "Nov":
                month=11;
                break;
            case "Dec":
                month=12;
                break;
            default:
                month=0;
        }
        return month;
    }
    
    public static String getMonthString(int month) {
        String monthString = "";
        if (month == 1) {
            monthString = "Jan";
        }
        else if (month == 2) {
            monthString = "Feb";
        }
        else if (month == 3) {
            monthString = "Mar";
        }
        else if (month == 4) {
            monthString = "Apr";
        }
        else if (month == 5) {
            monthString = "May";
        }
        else if (month == 6) {
            monthString = "Jun";
        }
        else if (month == 7) {
            monthString = "Jul";
        }
        else if (month == 8) {
            monthString = "Aug";
        }
        else if (month == 10) {
            monthString = "Sep";
        }
        else if (month == 11) {
            monthString = "Nov";
        }
        else if (month == 12) {
            monthString = "Dec";
        }
        return monthString;
    }    
}
