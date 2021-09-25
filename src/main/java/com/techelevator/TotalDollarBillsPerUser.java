package com.techelevator;

import java.text.NumberFormat;
import java.util.Locale;

public class TotalDollarBillsPerUser {

    private int oneDollarBills = 0;
    private int twoDollarBills = 0;
    private int fiveDollarBills = 0;
    private int tenDollarBills = 0;

    public TotalDollarBillsPerUser(int oneDollarBills, int twoDollarBills, int fiveDollarBills, int tenDollarBills) {
        this.oneDollarBills = oneDollarBills * 1;
        this.twoDollarBills = twoDollarBills * 2;
        this.fiveDollarBills = fiveDollarBills * 5;
        this.tenDollarBills = tenDollarBills * 10;
    }

    public String getUserBalance(){
        int total = this.oneDollarBills + this.twoDollarBills + this.fiveDollarBills + this.tenDollarBills;
        Locale locale = new Locale("en", "US");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
        return dollarFormat.format(total);
    }

    public static int[] parseMoney(String dollarBills){
        String[] numberDollarBillsInText = dollarBills.split(",");
        int[] result = new int[numberDollarBillsInText.length];
        for(int index=0;index<numberDollarBillsInText.length;index++){
            result[index] = Integer.parseInt(numberDollarBillsInText[index]);
        }
        return result;
    }
}
