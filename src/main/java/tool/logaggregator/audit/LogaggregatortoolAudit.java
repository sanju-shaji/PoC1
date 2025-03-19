package tool.logaggregator.audit;

import tool.logaggregator.constants.LogAggregatorToolConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * class for adding an audit whenever the logfiles get processed
 */
public class LogaggregatortoolAudit {

    /**
     * method to add the audit to audit table
     *
     * @param
     */
    public static void addAudit(String userInputFilePath, int logFileCount, String logFileNames, String logFileProcessResult, String sortedFilePath, String errorMessage) {
        String addAuditQuery = LogAggregatorToolConstants.ADD_AUDIT_QUERY;
        try {
            Connection connection = DriverManager.getConnection(LogAggregatorToolConstants.DB_URL, LogAggregatorToolConstants.DB_USERNAME, LogAggregatorToolConstants.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(addAuditQuery);
            preparedStatement.setString(1, userInputFilePath);
            preparedStatement.setInt(2, logFileCount);
            preparedStatement.setString(3, logFileNames);
            preparedStatement.setString(4, logFileProcessResult);
            preparedStatement.setString(5, sortedFilePath);
            preparedStatement.setString(6, errorMessage);
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println("Audit Error: " + exception.getMessage());
        }
    }
}
