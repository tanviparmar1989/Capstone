package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private List<Products> productList = new ArrayList<>();
    //private Map<VendingMachineUser, TotalDollarBillsPerUser> machineUsers = new HashMap<>();
    private TotalDollarBillsPerUser customerMoney = new TotalDollarBillsPerUser(0,0,0,0);
    private int balance;
    private String[] userSelectedProductsSlotNumbers;

/*
    public void acceptOrders(int slotNumber, int quantity){
        //see how u can get user input and then pull a product from 'productList' to create a Product object so as to
        //then create a user object and call its 'boughtItems' method by passing the product created.

    }
    */

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void acceptMoney(){
        Scanner userInput = new Scanner(System.in);
        System.out.println(" Please enter dollar bills as follows: ");
        System.out.println(" number of 1 dollar bills,number of 2 dollar bills,number of 5 dollar bills,number of 10 dollar bills  ");
        System.out.println("                                              ");
        System.out.println(" Example: If you would like to enter 2 ten dollar bills: 0,0,0,2");
        System.out.println("Please enter dollar bills:");
        String userEnteredBills = userInput.nextLine();
        int[] enteredCoins = TotalDollarBillsPerUser.parseMoney(userEnteredBills);
        customerMoney = new TotalDollarBillsPerUser(enteredCoins[0], enteredCoins[1], enteredCoins[2], enteredCoins[3]);
    }

    public void selectProduct(){
        Scanner userInput = new Scanner(System.in);
        System.out.println(" \nPlease enter slot number of preferred item: ");
        String userChosenItem = userInput.nextLine();
        String[] slotsNumbersOfSelectedProducts = userChosenItem.split(",");
        userSelectedProductsSlotNumbers = slotsNumbersOfSelectedProducts;
    }

    public void dispenseProducts(){
        System.out.println("Dispensing products: ");
        for(String userItem: userSelectedProductsSlotNumbers){
            for(Products product: this.productList){
                String slot = product.getSlotLocation();
                if(userItem.equalsIgnoreCase(slot)){
                    AuditLog.log("Starting Quantity " + product.getQuantity());
                    AuditLog.log("Starting balance " + getUserBalance());
                    product.setQuantity(product.getQuantity()-1);
                    AuditLog.log("Reduced quantity of "+product.getProductName()+" by 1.");
                    double price = product.getPrice();
                    AuditLog.log("Reduced money balance by "+ price + "dollars. Now balance is" + getUserBalance());
                    this.balance -= price;
                    System.out.println("Dispensed item "+product.getProductName());
                    AuditLog.log("Dispensed item "+product.getProductName());
                }
            }
        }
    }

    public void disperseChange(){
        if(this.balance > 0) {
            AuditLog.log("Current user balance is: "+getUserBalance());
            System.out.println("Your change is: " + getUserBalance());
            setBalance(0);
            AuditLog.log("set user balance to zero and provided change to user.");
            AuditLog.log("Now balance is: " + getUserBalance());
        }
    }

    public String getUserBalance(){
        return customerMoney.getUserBalance();
    }

    public void displayUserBalance(){
        System.out.println(System.lineSeparator() + "Current Money Provided >>> " + getUserBalance()+"\n");
    }

    public void displayProducts(){
        System.out.println("\nList of items:\n");
        //System.out.println("Slot Name        Price   Type   Quantity");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %10s %10s %10s %10s", "Code", "Product Name", "Price", "Type", "Quantity");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");

        for(Products product: productList){
            if(product.getQuantity()==0){
                System.out.println(String.format("%10s   %10s  %10s.2f  %10s  SOLD OUT",
                        product.getSlotLocation(), product.getProductName(), product.getPrice(), product.getType()));
            }else{
                System.out.println(String.format("%10s   %10s  %10.2f  %10s  %10d",
                        product.getSlotLocation(), product.getProductName(), product.getPrice(), product.getType(), product.getQuantity()));
            }
        }
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
                //revise what needs to be done here
            }

        }
    }
}
