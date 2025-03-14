package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * class for writing the sorted logdata to a single log file
 */
public class LogWriter {
    String outputDirectory = LogAggregatorToolConstants.OUTPUT_DIRECTORY;
    String currentDateTime = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss").format(new Date());
    public String sortedLogPath = outputDirectory + LogAggregatorToolConstants.SORTED_FILE_NAME + currentDateTime + LogAggregatorToolConstants.LOG_EXTENSION;
    File outfile = new File(outputDirectory);

    /**
     * method for writing the sorted logdata to a log file
     *
     * @param args
     * @return path of the new log file
     */
    public boolean writeFile(String[] args) {
        try {
            if (!outfile.exists()) {
                return false;
            }
            LogSorter fileSorter = new LogSorter();
            ArrayList<String> fileData = new ArrayList<>();
            fileData = fileSorter.sortFile(args);
            String sortedFilePath = sortedLogPath;
            {
                File file = new File(sortedFilePath);
                FileWriter writer = new FileWriter(file);
                for (String line : fileData) {
                    writer.write(line);
                    writer.write("\n");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
