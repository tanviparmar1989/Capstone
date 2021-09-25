package com.techelevator;

public enum dollarBills {
    ONE_DOLLAR(1),  TWO_DOLLAR(2), FIVE_DOLLAR(5), TEN_DOLLAR(10);

    private int value;

    dollarBills(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }



}
