package tool.logaggregator.audit;

import tool.logaggregator.filehandler.LogWriter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LogaggregatortoolAudit {
    private  static final String auditUrl="jdbc:mysql://localhost:3306/logaggregatoraudit";
    private static final String userName="root";
    private static final String password="Abc@1234";

    LogWriter logWriter=new LogWriter();




    public void auditEntry(String[] args){
        String userInputFilePath=args[0];
        File logFile=new File(userInputFilePath);
        String[] logFiles= logFile.list();
        int logFileCount=userInputFilePath.length();

        String sortedFilePath=logWriter.sortedLogPath;





        try
        {
            Connection connection= DriverManager.getConnection(auditUrl,userName,password);

        }
        catch (SQLException e) {
            System.out.println("connection failed :"+e.getMessage());
        }

    }





}
