package com.techelevator;

import java.text.NumberFormat;
import java.util.Locale;

public class TotalDollarBillsPerUser {

    private int oneDollarBills = 0;
    private int twoDollarBills = 0;
    private int fiveDollarBills = 0;
    private int tenDollarBills = 0;
    private double totalBalance;


    public TotalDollarBillsPerUser(int oneDollarBills, int twoDollarBills, int fiveDollarBills, int tenDollarBills) {
        this.oneDollarBills = oneDollarBills * 1;
        this.twoDollarBills = twoDollarBills * 2;
        this.fiveDollarBills = fiveDollarBills * 5;
        this.tenDollarBills = tenDollarBills * 10;
        this.totalBalance = this.oneDollarBills + this.twoDollarBills + this.fiveDollarBills + this.tenDollarBills;
    }

//    private void calculateUserBalance(){
//        this.totalBalance = this.oneDollarBills + this.twoDollarBills + this.fiveDollarBills + this.tenDollarBills;
//    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance -= totalBalance;
    }

    public String getUserBalance(){
        double total = getTotalBalance();
        Locale locale = new Locale("en", "US");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
        return dollarFormat.format(total);
    }

    public static int[] parseMoney(String dollarBills){
        String[] numberDollarBillsInText = dollarBills.split(",");
        int[] result = new int[numberDollarBillsInText.length];
        try{
            for(int index=0;index<numberDollarBillsInText.length;index++){
                result[index] = Integer.parseInt(numberDollarBillsInText[index]);
            }
        }catch(NumberFormatException e){
            AuditLog.log(e.getMessage());
            System.out.println("Invalid input!");
        }
        AuditLog.log("Changed customer money into integer array. Contents are: " + result[0]+" " + result[1]+" " + result[2]+" " + result[3]);
        return result;
    }
}
