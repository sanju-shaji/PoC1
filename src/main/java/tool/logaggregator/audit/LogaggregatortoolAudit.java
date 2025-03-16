package tool.logaggregator.audit;

import tool.logaggregator.constants.LogAggregatorToolConstants;
import tool.logaggregator.filehandler.LogFileProcessor;
import tool.logaggregator.filehandler.LogWriter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogaggregatortoolAudit {
    private  static final String auditUrl="jdbc:mysql://localhost:3306/logaggregatoraudit";
    private static final String userName="root";
    private static final String password="Abc@1234";

    LogWriter logWriter=new LogWriter();




    public void addAudit(String[] args){
        String userInputFilePath=args[0];
        File logFile=new File(userInputFilePath);
        String[] logFilesArray= logFile.list();
        String logFileNames=String.join(",  ",logFilesArray);
        int logFileCount=logFilesArray.length;
        String result=LogFileProcessor.result? "success" : "Failed";
        String sortedFilePath=LogFileProcessor.result?logWriter.sortedLogPath:null;
        String dateTime=new SimpleDateFormat(LogAggregatorToolConstants.SIMPLE_DATE_TIME_PATTERN_AUDIT).format(new Date());
        String errorMessage=null;
        errorMessage= LogFileProcessor.error;
        String auditQuery="INSERT INTO audit(folder_path, file_count, file_names, operation_datetime, result, output_file_name, error_message) VALUES(?, ?, ?, ?, ?, ?, ?)";




        try
        {
            Connection connection= DriverManager.getConnection(auditUrl,userName,password);
            PreparedStatement preparedStatement=connection.prepareStatement(auditQuery);
            preparedStatement.setString(1,userInputFilePath);
            preparedStatement.setInt(2,logFileCount);
            preparedStatement.setString(3,logFileNames);
            preparedStatement.setString(4,dateTime);
            preparedStatement.setString(5,result);
            preparedStatement.setString(6,sortedFilePath);
            preparedStatement.setString(7,errorMessage);

            preparedStatement.execute();



        }



        catch (SQLException e) {
            System.out.println("Audit Error: "+e.getMessage());
        }

    }





}
