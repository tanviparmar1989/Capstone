package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineUser {

    //
    private List<Product> itemsBought = new ArrayList<>();
    private double accountBalance;

    //populate list above with all items bought by single user
    //we need a method to poulate this list which will be called from z vending machine class by creating a user class

    public void boughtItems(Product item){
        itemsBought.add(item);
    }

    public void billCustomer(){
        //go through 'itemsBought' list and add quantity, then display receipt to user
    }

    public void receiveMoneyFromUser(){
        //see how u can take in the money
    }
}
