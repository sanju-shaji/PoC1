package tool.logaggregator.filehandler;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;

/**
 * class for writing the sorted logdata to a single log file
 */
public class LogWriter {
    String outputDirectory = "D:\\Internship\\SortedFiles";
    public String sortedLogPath = outputDirectory + "\\sortedLog" + UUID.randomUUID() + ".log";
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
