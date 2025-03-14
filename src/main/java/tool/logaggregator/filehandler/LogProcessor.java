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
            LogSorter fileSorter = new LogSorter();
            LogWriter logWriter = new LogWriter();
            fileSorter.sortFile(args);
            boolean isFileProcessed = logWriter.writeFile(args);
            if (!isFileProcessed) {
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_FAILED);
            } else {
                System.out.println(LogAggregatorToolConstants.FILE_PROCESSING_SUCCESS);
                System.out.println(LogAggregatorToolConstants.SORTED_FILE_PATH+logWriter.sortedLogPath);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
