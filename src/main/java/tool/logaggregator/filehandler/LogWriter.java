package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * class for writing the sorted logdata to a single log file
 */
public class LogWriter {
    public String outputFilePath;
    String currentDateTime = new SimpleDateFormat(LogAggregatorToolConstants.FILE_NAME_DATETIME_FORMAT).format(new Date());
    String sortedLogPath = LogAggregatorToolConstants.SORTED_FILE_NAME + currentDateTime + LogAggregatorToolConstants.LOG_EXTENSION;

    /**
     * method for writing the sorted logdata to a log file
     *
     * @return path of the new log file
     */
    public boolean writeLogFile(ArrayList sortedData) {
        try {
            String sortedFilePath = sortedLogPath;
            Scanner scanner = new Scanner(System.in);
            System.out.println(LogAggregatorToolConstants.GIVE_OUTPUT_FOLDER_PATH);
            String outputFolder = scanner.nextLine();
            {
                File file = new File(outputFolder + sortedFilePath);
                FileWriter writer = new FileWriter(file);
                for (Object line : sortedData) {
                    writer.write((String) line);
                    writer.write(LogAggregatorToolConstants.NEW_LINE);
                    outputFilePath = outputFolder + sortedFilePath;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
