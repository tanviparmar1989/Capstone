package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private List<Product> productList = new ArrayList<>();
    private double balance;

    public void acceptOrders(int slotNumber, int quantity){
        //see how u can get user input and then pull a product from 'productList' to create a Product object so as to
        //then create a user object and call its 'boughtItems' method by passing the product created.

    }

    public void displayProducts(){
        System.out.println("\nList of items:\n");
        //System.out.println("Slot Name        Price   Type   Quantity");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %10s %10s %10s %10s", "Code", "Product Name", "Price", "Type", "Quantity");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");

        for(Product product: productList){
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
                    productList.add(new Product(products[0], products[1], Double.parseDouble(products[2]), products[3]));
                }

            } catch (FileNotFoundException e) {
                //revise what needs to be done here
            }

        }
    }
}
