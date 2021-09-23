package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class AuditLog {
    private static PrintWriter logWriter;
    private static LocalDateTime current = LocalDateTime.now();

    public static void log(String message){
        String logDate = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(current);
        File logFile = new File("logs/"+logDate+ " Log.txt");
        try{
            if(logWriter == null) logWriter = new PrintWriter(new FileWriter(logFile));
            logWriter.println(logDate + " " + message);
        }catch(IOException e){
            throw new AuditLogException(e.getMessage());
        }finally{
            logWriter.flush();
        }
    }
}
