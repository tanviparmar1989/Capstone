package com.techelevator.view;

import com.techelevator.CapstoneAuditLogException;
import com.techelevator.Products;
import com.techelevator.VendingMachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

public class SalesReport {
    private static PrintWriter logWriter;


    public static void displayTotalSales(String message, Map<String, Integer> salesReport_ProductList) throws CapstoneAuditLogException {

        LocalDateTime current = LocalDateTime.now();
        String logDate = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(current);
        String logDateFormatted = logDate
                .replaceAll(",", "")
                .replaceAll(":| ", "_");

        File logFile = new File("SalesReport/" + logDateFormatted + ".log");
        try {
            if (logWriter == null) logWriter = new PrintWriter(new FileWriter(logFile));
            logWriter.println("Sales Report: " + LocalDateTime.now() + "\n");
            for (Map.Entry<String, Integer> salesReportItem : salesReport_ProductList.entrySet()) {
                logWriter.println(salesReportItem.getKey() + "|" + salesReportItem.getValue() + "\n\n");
                }
            logWriter.println(message);

            } catch (IOException e) {
                throw new CapstoneAuditLogException(e.getMessage());
            } finally {
            logWriter.flush();
        }


    }
    /*public static void displaySalesReport(Map<String, Integer> salesReport_ProductList){
        if(logWriter == null) logWriter = new PrintWriter(new FileWriter(logFile));
        {
            logWriter.println("Sales Report: " + LocalDateTime.now() + "\n");
            for (Map.Entry<String, Integer> salesReportItem : salesReport_ProductList.entrySet()) {
                logWriter.println(salesReportItem.getKey() + "|" + salesReportItem.getValue());
            }
        }

    }

     */
}
