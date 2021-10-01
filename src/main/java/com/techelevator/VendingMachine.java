package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {

    private List<Products> productList = new ArrayList<>();
    private TotalDollarBillsPerUser customerMoney = new TotalDollarBillsPerUser(0,0,0,0);
    private String[] userSelectedProductsSlotNumbers;

    public static Map<String, Integer> allSalesReport = null;
    public static double allSales = 0;



    public boolean acceptMoney(){
        boolean hasEnteredValid = false;
        double totalPriceOfSelectedItems = 0;
        if(userSelectedProductsSlotNumbers != null){
            for(String slotNumberUser: userSelectedProductsSlotNumbers){
                for(Products product: this.productList){
                    String slot = product.getSlotLocation();
                    if(slotNumberUser.equalsIgnoreCase(slot)){
                        totalPriceOfSelectedItems += product.getPrice();
                    }
                }
            }
            if(customerMoney.getTotalBalance()<totalPriceOfSelectedItems){
                Scanner userInput = new Scanner(System.in);
                System.out.println(" Please enter dollar bills as follows: ");
                System.out.println(" number of 1 dollar bills,number of 2 dollar bills,number of 5 dollar bills,number of 10 dollar bills  ");
                System.out.println("                                              ");
                System.out.println(" Example: If you would like to enter 2 ten dollar bills: 0,0,0,2");
                System.out.println("Please enter dollar bills:");
                String userEnteredBills = userInput.nextLine();
                int[] enteredBills = TotalDollarBillsPerUser.parseMoney(userEnteredBills);
                for(int bill: enteredBills){
                    if(bill>0) hasEnteredValid = true;
                }
                if(hasEnteredValid){
                    if(!(customerMoney.getTotalBalance()>0)) {
                        customerMoney = new TotalDollarBillsPerUser(enteredBills[0], enteredBills[1], enteredBills[2], enteredBills[3]);
                        AuditLog.log("Prompted user to enter money and then accepted the money and created a TotalDollarBillsPerUser object.");
                    }else{
                        TotalDollarBillsPerUser additionalCustomerMoney =
                                new TotalDollarBillsPerUser(enteredBills[0], enteredBills[1], enteredBills[2], enteredBills[3]);
                        customerMoney.setTotalBalance(customerMoney.getTotalBalance() + additionalCustomerMoney.getTotalBalance());
                        AuditLog.log("Prompted user to enter money and then added new money to existing balance.");
                    }

                }else{
                    System.out.println("You have not entered any money.");
                }
                return hasEnteredValid;
            }else{
                return true;
            }
        }else{
            Scanner userInput = new Scanner(System.in);
            System.out.println(" Please enter dollar bills as follows: ");
            System.out.println(" number of 1 dollar bills,number of 2 dollar bills,number of 5 dollar bills,number of 10 dollar bills  ");
            System.out.println("                                              ");
            System.out.println(" Example: If you would like to enter 2 ten dollar bills: 0,0,0,2");
            System.out.println("Please enter dollar bills:");
            String userEnteredBills = userInput.nextLine();
            int[] enteredBills = TotalDollarBillsPerUser.parseMoney(userEnteredBills);
            for(int bill: enteredBills){
                if(bill>0) hasEnteredValid = true;
            }
            if(hasEnteredValid){
                if(!(customerMoney.getTotalBalance()>0)) {
                    customerMoney = new TotalDollarBillsPerUser(enteredBills[0], enteredBills[1], enteredBills[2], enteredBills[3]);
                    AuditLog.log("Prompted user to enter money and then accepted the money and created a TotalDollarBillsPerUser object.");
                }else{
                    TotalDollarBillsPerUser additionalCustomerMoney =
                            new TotalDollarBillsPerUser(enteredBills[0], enteredBills[1], enteredBills[2], enteredBills[3]);
                    customerMoney.setTotalBalance(customerMoney.getTotalBalance() + additionalCustomerMoney.getTotalBalance());
                    AuditLog.log("Prompted user to enter money and then added new money to existing balance.");
                }

            }else{
                System.out.println("You have not entered any money.");
            }
            return hasEnteredValid;
        }
    }


    public void selectProduct(){
        Scanner userInput = new Scanner(System.in);
        System.out.println(" \nPlease enter slot number of preferred item: ");
        String userChosenItem = userInput.nextLine();
        String[] slotsNumbersOfSelectedProducts = userChosenItem.trim().split("\\s*,\\s*");
        userSelectedProductsSlotNumbers = slotsNumbersOfSelectedProducts;
        AuditLog.log("Captured customer selections. Slot numbers are:");
        for(String slot: userSelectedProductsSlotNumbers){
            AuditLog.log(slot);
        }
    }

    public void dispenseProducts(){
        System.out.println("Dispensing products: ");
        AuditLog.log("Dispensing products");
        List<Double> totalSales = new ArrayList<>();
        double totalSalesAmount = 0.0;
        int numberOfItemSold;
        Set<String> dispensedSlotNumbers = new HashSet<>();
        /*
        Map<String, Integer> salesReport_ProductList = new HashMap<>();
        for(Products productName: this.productList){
            salesReport_ProductList.put(productName.getProductName(), 0);
        }

         */
        if (allSalesReport == null) {
            allSalesReport = new HashMap<>();
            for (Products productName : this.productList) {
                allSalesReport.put(productName.getProductName(), 0);
            }
        }
        for(String slotNumberUser: userSelectedProductsSlotNumbers){
            for(Products product: this.productList){
                String slot = product.getSlotLocation();
                if(slotNumberUser.equalsIgnoreCase(slot)){
                    AuditLog.log("Starting Quantity " + product.getQuantity());
                    AuditLog.log("Starting balance " + getUserBalance());
                    if(product.getQuantity()<=0){
                        System.out.println(product.getProductName()+" is SOLD OUT.");
                        break;
                    }
                    product.setQuantity(product.getQuantity()-1);
                    AuditLog.log("Reduced quantity of "+product.getProductName()+" by 1. New quantity is " + product.getQuantity());
                    double price = product.getPrice();
                    customerMoney.reduceTotalBalance(price);
                    AuditLog.log("Reduced money balance by "+ price + " dollars. Now balance is " + getUserBalance());
                    String productTypeSpecificStatement = "";
                    if(product.getType().equalsIgnoreCase("Chip")) productTypeSpecificStatement = "Crunch Crunch, Yum!";
                    else if (product.getType().equalsIgnoreCase("Candy")) productTypeSpecificStatement = "Munch Munch, Yum!";
                    else if (product.getType().equalsIgnoreCase("Drink")) productTypeSpecificStatement = "Glug Glug, Yum!";
                    else if (product.getType().equalsIgnoreCase("Gum")) productTypeSpecificStatement = "Chew Chew, Yum!";
                    System.out.println("Dispensed item "+product.getProductName()+", " + "cost: "+product.getPrice()+", "+"Remaining balance: "+ getUserBalance());
                    System.out.println(productTypeSpecificStatement);
                    numberOfItemSold = 5 - product.getQuantity();
                    String productName = product.getProductName();
                    //salesReport_ProductList.replace(productName, 0, numberOfItemSold);
                    allSalesReport.replace(product.getProductName(), allSalesReport.get(product.getProductName())+1);
                    //allSalesReport.put(productName, allSalesReport.getOrDefault(productName, 0) + numberOfItemSold);
                    allSales += product.getPrice();
                    AuditLog.log("Dispensed item "+product.getProductName()+", " + "cost: "+product.getPrice()+", "+"Remaining balance: "+ getUserBalance());
                    AuditLog.log(productTypeSpecificStatement);
                    dispensedSlotNumbers.add(slotNumberUser);
                    totalSales.add(product.getPrice());
                }
            }

        }
        checkIfAllSlotsValid(userSelectedProductsSlotNumbers, dispensedSlotNumbers);
        for(double sale : totalSales){
            totalSalesAmount += sale;
        }
        AuditLog.log("Dispensed all products by reducing quantity of each and reducing money balance as well.");

    }

    public void checkIfAllSlotsValid(String[] userEnteredSlots, Set<String> dispensedSlotNumbers){
        for(String userEnteredSlot: userEnteredSlots){
            if(!dispensedSlotNumbers.contains(userEnteredSlot)) {
                System.out.println("The slot number entered: " + userEnteredSlot+ ", does not exist.");
                AuditLog.log("User entered a slot number: " + userEnteredSlot+ ",  that does not exist.");
            }
        }
    }

    public void disperseChange(){
        if(customerMoney.getTotalBalance() > 0) {
            AuditLog.log("Current user balance is: "+getUserBalance());
            System.out.println("\nYour change is: " + getUserBalance()+"\n");
            double changeDue = customerMoney.getTotalBalance();
            int change = (int)(Math.ceil(changeDue*100));
            int quarters = Math.round((int)change/25);
            change=change%25;
            int dimes = Math.round((int)change/10);
            change=change%10;
            int nickels = Math.round((int)change/5);
            //change=change%5;

            System.out.println("Quarters: " + quarters);
            System.out.println("Dimes: " + dimes);
            System.out.println("Nickels: " + nickels);
            customerMoney.reduceTotalBalance(customerMoney.getTotalBalance());
            AuditLog.log("set user balance to zero and provided change to user.");
            AuditLog.log("Now balance is: " + getUserBalance());
        }
    }

    public String getUserBalance(){
        return customerMoney.getUserBalance();
    }

    public String displayUserBalance(){
        System.out.println(System.lineSeparator() + "Current Money Provided >>> " + getUserBalance()+"\n");
        AuditLog.log("Displayed user balance to customer and the balance is: "+ getUserBalance());
        return getUserBalance();
    }

    public void displayProducts(){
        System.out.println("\nList of items:\n");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %20s %22s %10s %15s", "Code", "Product Name", "Price", "Type", "Quantity");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");

        for(Products product: productList){
            if(product.getQuantity()==0){
                System.out.println(String.format("%8s   %20s  %20.2f  %10s  SOLD OUT",
                        product.getSlotLocation(), product.getProductName(), product.getPrice(), product.getType()));
            }else{
                System.out.println(String.format("%8s   %20s  %20.2f  %10s  %10d",
                        product.getSlotLocation(), product.getProductName(), product.getPrice(), product.getType(), product.getQuantity()));
            }
        }
        AuditLog.log("Displayed products menu.");
    }

    public void restock() {
        //read input file and populate the list by making product objects out of contents of file
        File file = new File("vendingmachine.csv");
        if (file.exists() && file.canRead()) {
            try (Scanner readFile = new Scanner(file)) {
                while (readFile.hasNext()) {
                    String[] products = readFile.nextLine().split("\\|", 4);
                    this.productList.add(new Products(products[0], products[1], Double.parseDouble(products[2]), products[3]));
                }

            } catch (FileNotFoundException e) {
                AuditLog.log(e.getMessage());
            }

        }
    }

}
