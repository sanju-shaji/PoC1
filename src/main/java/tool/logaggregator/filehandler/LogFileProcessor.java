package tool.logaggregator.filehandler;

import tool.logaggregator.audit.LogaggregatortoolAudit;
import tool.logaggregator.constants.LogAggregatorToolConstants;
import java.io.File;
import java.util.ArrayList;

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
            LogReader logReader = new LogReader();
            ArrayList<String> mergedFileData = logReader.readLogData(args[0]);
            LogSorter logFileSorter = new LogSorter();
            ArrayList<String> sortedData = logFileSorter.sortLogFile(mergedFileData);
            LogWriter logFileWriter = new LogWriter();
            boolean isFileProcessed = logFileWriter.writeLogFile(sortedData);
            if (isFileProcessed) {
                String sortedFilePath = logFileWriter.outputFilePath;
                LogaggregatortoolAudit.addAudit(userFilePath, logFileCount, logFileNames, LogAggregatorToolConstants.PROCESS_SUCCESS, sortedFilePath, null);
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_SUCCESS + LogAggregatorToolConstants.NEW_LINE + LogAggregatorToolConstants.SORTED_FILE_PATH + logFileWriter.outputFilePath);
            } else {
                LogaggregatortoolAudit.addAudit(userFilePath, logFileCount, logFileNames, LogAggregatorToolConstants.PROCESS_FAILED, null, error);
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_FAILED);
            }
        } catch (Exception exception) {
            error = exception.getMessage();
        }
    }
}
