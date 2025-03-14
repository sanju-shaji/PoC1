package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;

/**
 * logprocessor class for calling logreader,logsorter,logwriter
 */
public class LogProcessor {

    /**
     * call all the logprocessing classes and verify if file processing is succes or not
     *
     * @param args
     */
    public void processFile(String[] args) {
        try {
            LogSorter logFileSorter = new LogSorter();
            LogWriter logFileWriter = new LogWriter();
            logFileSorter.sortFile(args);
            boolean isFileProcessed = logFileWriter.writeFile(args);
            if (!isFileProcessed) {
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_FAILED);
            } else {
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_SUCCESS + "\n" + LogAggregatorToolConstants.SORTED_FILE_PATH + logFileWriter.sortedLogPath);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
