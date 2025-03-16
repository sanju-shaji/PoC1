package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;

/**
 * logprocessor class for calling logreader,logsorter,logwriter
 */
public class LogFileProcessor {

    public static String error;
    public static boolean result;

    /**
     * call all the logprocessing classes and verify if file processing is succes or not
     *
     * @param args
     */
    public void processLogFile(String[] args) {
        try {
            String userFilePath = args[0];
            LogSorter logFileSorter = new LogSorter();
            LogWriter logFileWriter = new LogWriter();
            logFileSorter.sortLogFile(userFilePath);
            boolean isFileProcessed = logFileWriter.witeLogFile(userFilePath);
            if (isFileProcessed) {
                result=true;
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_SUCCESS + "\n" + LogAggregatorToolConstants.SORTED_FILE_PATH + logFileWriter.sortedLogPath);
            } else {
                result=false;
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_FAILED);
            }
        } catch (Exception exception) {
            error=exception.getMessage();
        }
    }
}
