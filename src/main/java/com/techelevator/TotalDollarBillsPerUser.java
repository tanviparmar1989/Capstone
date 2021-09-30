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


    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void reduceTotalBalance(double totalBalance) {
        this.totalBalance -= totalBalance;
    }

    public String getUserBalance() {
        double total = getTotalBalance();
        Locale locale = new Locale("en", "US");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
        return dollarFormat.format(total);
    }

    public static int[] parseMoney(String dollarBills) {
        String[] numberDollarBillsInText = dollarBills.trim().split("\\s*,\\s*");
        int[] result = new int[numberDollarBillsInText.length];
        try {
            for (int index = 0; index < numberDollarBillsInText.length; index++) {
                result[index] = Integer.parseInt(numberDollarBillsInText[index]);
            }
        } catch (NumberFormatException e) {
            AuditLog.log(e.getMessage());
            System.out.println("Invalid input!. Please input only numbers in the format 1,1,1,1.");
            for (int index = 0; index < numberDollarBillsInText.length; index++) {
                result[index] = 0;
            }
        }
        AuditLog.log("Changed customer money into integer array. Contents are: ");
        for (int index = 0; index < numberDollarBillsInText.length; index++) {
            AuditLog.log(Integer.toString(result[index]));
        }
        return result;
    }
}
