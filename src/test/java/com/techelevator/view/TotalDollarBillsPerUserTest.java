package com.techelevator.view;

import com.techelevator.TotalDollarBillsPerUser;
import org.junit.Assert;
import org.junit.Test;

public class TotalDollarBillsPerUserTest {

    @Test
    public void testGetUserBalance_HappyPath() {
        // given
        TotalDollarBillsPerUser totalDollarBillsPerUser = new TotalDollarBillsPerUser(0, 0, 0, 1);

        // when
        String userBalance = totalDollarBillsPerUser.getUserBalance();

        // then
        Assert.assertEquals("$10.00", userBalance);
    }

    @Test
    public void testGetUserBalance_AllZeros() {
        // given
        TotalDollarBillsPerUser totalDollarBillsPerUser = new TotalDollarBillsPerUser(0, 0, 0, 0);

        // when
        String userBalance = totalDollarBillsPerUser.getUserBalance();

        // then
        Assert.assertEquals("$0.00", userBalance);
    }

    @Test
    public void testGetTotalBalance() {
        TotalDollarBillsPerUser totalDollarBillsPerUser = new TotalDollarBillsPerUser(1, 1, 1, 2);
        Assert.assertEquals(28.0, totalDollarBillsPerUser.getTotalBalance(),1);
    }
}
