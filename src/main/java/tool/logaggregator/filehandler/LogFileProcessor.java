package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;

import java.util.ArrayList;

/**
 * class for handling the logfile processes
 */
public class LogFileProcessor {

    /**
     * method which execute the file processes
     * logFileReader,logFileSorter and logFileWriter are executed to
     * process the inputlogfiles and produce a single merged and sorted logfile
     *
     * @param args
     */
    public void processLogFile(String[] args) {
        try {
            String userFilePath = args[0];
            LogReader logReader = new LogReader();
            ArrayList<String> mergedFileData = logReader.readLogData(args[0]);
            LogSorter logFileSorter = new LogSorter();
            ArrayList<String> sortedData = logFileSorter.sortLogFile(mergedFileData);
            LogWriter logFileWriter = new LogWriter();
            boolean isFileProcessed = logFileWriter.writeLogFile(sortedData);
            if (isFileProcessed) {
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_SUCCESS + LogAggregatorToolConstants.NEW_LINE + LogAggregatorToolConstants.SORTED_FILE_PATH + logFileWriter.outputFilePath);
            } else {
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_FAILED);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
