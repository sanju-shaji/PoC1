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
    String outputFolder;
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
            System.out.println(LogAggregatorToolConstants.GIVE_OUTPUT_FOLDER_PATH);
            if (!verifyUserInputpath()) {}
            {
                File outputLogFile = new File(outputFolder + sortedFilePath);
                FileWriter fileWriter = new FileWriter(outputLogFile);
                for (Object line : sortedData) {
                    fileWriter.write((String) line);
                    fileWriter.write(LogAggregatorToolConstants.NEW_LINE);
                }
                outputFilePath = outputFolder + sortedFilePath;

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private boolean verifyUserInputpath() {
        Scanner scanner = new Scanner(System.in);
        outputFolder = scanner.nextLine();
        try {
            File file = new File(outputFolder);
            if (file.exists() || file.isDirectory()) {
                return true;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        System.out.println(LogAggregatorToolConstants.INVALID_PATH);
        verifyUserInputpath();
        return false;
    }
}
