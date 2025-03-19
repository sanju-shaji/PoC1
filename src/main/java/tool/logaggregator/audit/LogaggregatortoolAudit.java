package tool.logaggregator.audit;

import tool.logaggregator.constants.LogAggregatorToolConstants;
import tool.logaggregator.dao.AuditData;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * class for adding an audit whenever the logfiles get processed
 */
public class LogaggregatortoolAudit {

    /**
     * This method loads the property file
     *
     * @return
     */
    private static Properties getProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStreamer = new FileInputStream(LogAggregatorToolConstants.PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStreamer);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return properties;
    }

    /**
     * This method adds data to the database
     *
     * @param auditData
     */
    public void addAudit(AuditData auditData) {
        String addAuditQuery = LogAggregatorToolConstants.ADD_AUDIT_QUERY;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(addAuditQuery);
            preparedStatement.setString(1, auditData.getFolderPath());
            preparedStatement.setInt(2, auditData.getFileCount());
            preparedStatement.setString(3, auditData.getFileNames());
            preparedStatement.setString(4, auditData.getResult());
            preparedStatement.setString(5, auditData.getOutputFileName());
            preparedStatement.setString(6, auditData.getErrorMessage());
            preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * This method establishes the connection with database
     *
     * @return
     */
    private Connection getConnection() {
        Connection connection = null;
        Properties properties = getProperties();
        try {
            connection = DriverManager.getConnection(properties.getProperty(LogAggregatorToolConstants.DB_URL), properties.getProperty(LogAggregatorToolConstants.DB_USERNAME), properties.getProperty(LogAggregatorToolConstants.DB_PASSWORD));
        } catch (SQLException exception) {
            System.out.println(exception.getMessage() + exception.getErrorCode());
        }
        return connection;
    }
}
