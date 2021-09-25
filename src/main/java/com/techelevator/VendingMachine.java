package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {

    private List<Product> productList = new ArrayList<>();
    //private Map<VendingMachineUser, TotalDollarBillsPerUser> machineUsers = new HashMap<>();
    private TotalDollarBillsPerUser customerMoney = new TotalDollarBillsPerUser(0,0,0,0);
    private static int balance;

/*
    public void acceptOrders(int slotNumber, int quantity){
        //see how u can get user input and then pull a product from 'productList' to create a Product object so as to
        //then create a user object and call its 'boughtItems' method by passing the product created.

    }
    */

    public static void setBalance(int balance) {
        VendingMachine.balance = balance;
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
        this.customerMoney = new TotalDollarBillsPerUser(enteredCoins[0], enteredCoins[1], enteredCoins[2], enteredCoins[3]);
    }

    public String[] selectProduct(){
        Scanner userInput = new Scanner(System.in);
        System.out.println(" Please enter slot number of preferred item: ");
        String userChosenItem = userInput.nextLine();
        String[] slotsNumbersOfSelectedProducts = userChosenItem.split(",");
        for(String product: slotsNumbersOfSelectedProducts){
            System.out.println(product);
        }
        return slotsNumbersOfSelectedProducts;
    }

    public String getUserBalance(){
        return customerMoney.getUserBalance();
    }

    public void displayProducts(){
        System.out.println("\nList of items:\n");
        System.out.println("Slot Name        Price   Type   Quantity");
        for(Product product: productList){
            if(product.getQuantity()==0){
                System.out.println(String.format("%s   %s  %.2f  %s  SOLD OUT",
                        product.getSlotLocation(), product.getProductName(), product.getPrice(), product.getType()));
            }else{
                System.out.println(String.format("%s   %s  %.2f  %s  %d",
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
                    productList.add(new Product(products[0], products[1], Double.parseDouble(products[2]), products[3]));
                }

            } catch (FileNotFoundException e) {
                //revise what needs to be done here
            }

        }
    }
}
