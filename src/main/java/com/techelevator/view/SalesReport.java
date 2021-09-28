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

public class SalesReport {
    private static PrintWriter logWriter;

    public static void salesReport(String message) throws CapstoneAuditLogException  {
        LocalDateTime current = LocalDateTime.now();
        String logDate = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(current);
        String logDateFormatted = logDate
                .replaceAll(",", "")
                .replaceAll(":| ", "_");

        File logFile = new File("SalesReport/" + logDateFormatted + ".log");
        try{
            if(logWriter == null) logWriter = new PrintWriter(new FileWriter(logFile));
           logWriter.println(message);
        }catch(IOException e){
            throw new CapstoneAuditLogException(e.getMessage());
        }finally{
            logWriter.flush();
        }


    }
}
