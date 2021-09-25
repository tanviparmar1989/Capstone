package com.techelevator;

public class Products {
    private String slotLocation;
    private String productName;
    private double price;
    private String type;
    private int quantity;

    public Products(){

    }

    public Products(String slotLocation, String productName, double price, String type){
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

}
