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

/**
 * class for adding an audit whenever the logfiles get processed
 */
public class LogaggregatortoolAudit {
    private static final String auditDbUrl = LogAggregatorToolConstants.DB_URL;
    private static final String dbUserName = LogAggregatorToolConstants.DB_USERNAME;
    private static final String dbPassword = LogAggregatorToolConstants.DB_PASSWORD;
    LogWriter logWriter = new LogWriter();

    /**
     * method to add the audit to audit table
     *
     * @param args
     */
    public void addAudit(String[] args) {
        String userInputFilePath = args[0];
        File logFile = new File(userInputFilePath);
        String[] logFilesArray = logFile.list();
        String logFileNames = String.join(LogAggregatorToolConstants.ARRAY_TO_STRING_DELIMITER, logFilesArray);
        int logFileCount = logFilesArray.length;
        String logFileProcessResult = LogFileProcessor.result ? LogAggregatorToolConstants.PROCESS_SUCCESS : LogAggregatorToolConstants.PROCESS_FAILED;
        String sortedFilePath = LogFileProcessor.result ? logWriter.sortedLogPath : null;
        String dateTime = new SimpleDateFormat(LogAggregatorToolConstants.SIMPLE_DATE_TIME_PATTERN_AUDIT).format(new Date());
        String errorMessage = null;
        errorMessage = LogFileProcessor.error;
        String addAuditQuery = LogAggregatorToolConstants.ADD_AUDIT_QUERY;
        try {
            Connection connection = DriverManager.getConnection(auditDbUrl, dbUserName, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(addAuditQuery);
            preparedStatement.setString(1, userInputFilePath);
            preparedStatement.setInt(2, logFileCount);
            preparedStatement.setString(3, logFileNames);
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, logFileProcessResult);
            preparedStatement.setString(6, sortedFilePath);
            preparedStatement.setString(7, errorMessage);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Audit Error: " + e.getMessage());
        }
    }
}
