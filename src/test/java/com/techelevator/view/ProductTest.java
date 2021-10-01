package com.techelevator.view;

import com.techelevator.Products;
import com.techelevator.TotalDollarBillsPerUser;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

    @Test
    public void testGetSlotLocation_HappyCode(){
        Products product = new Products("A1", "Potato Crisps", 0.0, "");
        Assert.assertEquals("A1", product.getSlotLocation());
    }

    @Test
    public void testGetSlotLocation_withEmptyString(){
        Products products = new Products("", "", 0.0, "");
        Assert.assertEquals("", products.getSlotLocation());
    }

    @Test
    public void testGetSlotLocation_withInvalidCode(){
        Products products = new Products("g1", "", 0.0, "");
        System.out.println(products.getSlotLocation());
    }

    @Test
    public void testGetProductName_HappyCode(){
        Products product = new Products("A1", "Potato Crisps", 0.0, "");
        Assert.assertEquals("Potato Crisps", product.getProductName());
    }

    @Test
    public void testGetProductName_NullString(){
        Products products = new Products("", "Potato Crisps", 0.0, "");
        Assert.assertEquals("Potato Crisps",products.getProductName());
    }

    @Test
    public void testGetPrice_HappyCode(){
        Products products = new Products("", "", 1.05, "");
        Assert.assertEquals(1.05, products.getPrice(), 2);
    }

    @Test
    public void testGetType(){
        Products products = new Products("", "", 0.0, "Candy");
        Assert.assertEquals("Candy", "Candy");
    }

    @Test
    public void testGetQuantity(){
        Products products = new Products("", "", 0.0, "");
        Assert.assertEquals(5, products.getQuantity());
    }


}
