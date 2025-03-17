package tool.logaggregator.filehandler;

import tool.logaggregator.audit.LogaggregatortoolAudit;
import tool.logaggregator.constants.LogAggregatorToolConstants;

import java.io.File;

/**
 * logprocessor class for calling logreader,logsorter,logwriter
 */
public class LogFileProcessor {
    public static String error;

    /**
     * call all the logprocessing classes and verify if file processing is succes or not
     *
     * @param args
     */
    public void processLogFile(String[] args) {
        try {
            String userFilePath = args[0];
            File logFile = new File(userFilePath);
            String[] logFilesArray = logFile.list();
            int logFileCount = logFilesArray.length;
            String logFileNames = String.join(LogAggregatorToolConstants.ARRAY_TO_STRING_DELIMITER, logFilesArray);
            LogSorter logFileSorter = new LogSorter();
            LogWriter logFileWriter = new LogWriter();
            String sortedFilePath = logFileWriter.sortedLogPath;
            logFileSorter.sortLogFile(userFilePath);
            boolean isFileProcessed = logFileWriter.witeLogFile(userFilePath);
            if (isFileProcessed) {
                LogaggregatortoolAudit.addAudit(userFilePath, logFileCount, logFileNames, LogAggregatorToolConstants.PROCESS_SUCCESS, sortedFilePath, null);
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_SUCCESS + LogAggregatorToolConstants.NEW_LINE + LogAggregatorToolConstants.SORTED_FILE_PATH + logFileWriter.sortedLogPath);
            } else {
                LogaggregatortoolAudit.addAudit(userFilePath, logFileCount, logFileNames, LogAggregatorToolConstants.PROCESS_FAILED, null, error);

                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_FAILED);
            }
        } catch (Exception exception) {
            error = exception.getMessage();
        }
    }
}
