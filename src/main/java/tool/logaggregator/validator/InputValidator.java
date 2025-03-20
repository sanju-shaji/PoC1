package tool.logaggregator.validator;

import tool.logaggregator.audit.LogaggregatortoolAudit;
import tool.logaggregator.constants.LogAggregatorToolConstants;
import tool.logaggregator.dao.AuditData;
import tool.logaggregator.util.LogAggregatorToolUtil;

import java.io.File;

/**
 * validation class to validate inputs the user input
 */
public class InputValidator {
    private final LogaggregatortoolAudit logaggregatortoolAudit = new LogaggregatortoolAudit();
    LogAggregatorToolUtil logAggregatorToolUtil = new LogAggregatorToolUtil();
    /**
     * menthod to check if user provides a folder path as an argument
     */
    private AuditData auditData;

    private boolean isCommandLineArgumentPresent(String[] args) {
        if (args.length == 0) {
            System.out.println(LogAggregatorToolConstants.NO_COMMAND_LINE_ARGUMENT);
            auditData = logAggregatorToolUtil.setDaoData(null, 0, null, LogAggregatorToolConstants.PROCESS_FAILED, null, LogAggregatorToolConstants.NO_COMMAND_LINE_ARGUMENT);
            logaggregatortoolAudit.addAudit(auditData);
            return false;
        }
        return true;
    }

    /**
     * method to check if the user provided path is a directory or not
     */
    private boolean isValidDirectory(String[] args) {
        String userFolderPath = args[0];
        File userFolder = new File(userFolderPath);
        if (!(userFolder.isDirectory())) {
            System.out.println(LogAggregatorToolConstants.INVALID_PATH);
            auditData = logAggregatorToolUtil.setDaoData(userFolderPath, 0,
                    null, LogAggregatorToolConstants.PROCESS_FAILED, null,
                    LogAggregatorToolConstants.INVALID_PATH);
            logaggregatortoolAudit.addAudit(auditData);
            return false;
        }
        return true;
    }

    /**
     * Method to check if the user input path is empty or not
     */
    private boolean isFolderEmpty(String[] args) {
        String userFolderPath = args[0];
        File userFolder = new File(userFolderPath);
        if (userFolder.list().length == 0) {
            auditData = logAggregatorToolUtil.setDaoData(userFolderPath, 0, null,
                    LogAggregatorToolConstants.PROCESS_FAILED, null,
                    LogAggregatorToolConstants.EMPTY_FOLDER);
            logaggregatortoolAudit.addAudit(auditData);
            System.out.println(LogAggregatorToolConstants.EMPTY_FOLDER);
            return false;
        }
        return true;
    }

    /**
     * Method to display the number of valid and invalid file and display processing
     */
    private void validateExtensions(String[] args) {
        String userFolderPath = args[0];
        System.out.println(LogAggregatorToolConstants.PROCESSING);
        File userFolder = new File(userFolderPath);
        String[] folderContents = userFolder.list();
        int nonLogFilesCount = 0;
        int logFileCount = 0;
        long totalFilesCount = folderContents.length;
        for (String files : folderContents) {
            if ((files.endsWith(LogAggregatorToolConstants.LOG_EXTENSION))) {
                logFileCount += 1;
            }
            nonLogFilesCount = (int) totalFilesCount - logFileCount;
        }
        System.out.println(LogAggregatorToolConstants.TOTAL_FILE_COUNT + totalFilesCount);
        if (nonLogFilesCount != 0) {
            System.out.println(nonLogFilesCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.INVALID_LOG_FILES);
        }
        System.out.println(logFileCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.VALID_LOG_FILES);
    }

    /**
     * validator method to run all the validation
     */
    public boolean validateUserInput(String[] args) {
        if (!isCommandLineArgumentPresent(args)) {
            return false;
        }
        if (!isValidDirectory(args) || !isFolderEmpty(args)) {
            return false;
        }
        validateExtensions(args);
        return true;
    }
}
