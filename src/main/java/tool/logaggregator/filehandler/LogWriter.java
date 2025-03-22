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
    public String sortedLogName = LogAggregatorToolConstants.SORTED_FILE_NAME + currentDateTime + LogAggregatorToolConstants.LOG_EXTENSION;

    /**
     * method for writing the sorted logdata to a log file
     *
     * @return path of the new log file
     */
    public boolean writeLogFile(ArrayList sortedData) {
        String sortedFileName = sortedLogName;
        System.out.println(LogAggregatorToolConstants.GIVE_OUTPUT_FOLDER_PATH);
        if (verifyUserInputpath())
            if (sortedData.isEmpty()) {
                System.out.println(LogAggregatorToolConstants.EMPTY_LOGFILE);
                return false;
            }
        File outputLogFile = new File(outputFolder + LogAggregatorToolConstants.SLASH + sortedFileName);
        try (FileWriter fileWriter = new FileWriter(outputLogFile)) {
            for (Object line : sortedData) {
                fileWriter.write((String) line);
                fileWriter.write(LogAggregatorToolConstants.NEW_LINE);
                outputFilePath = outputFolder + LogAggregatorToolConstants.SLASH + sortedFileName;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return true;
    }

    /**
     * Method for checking if the user input path for storing the sorted file is valid or not
     *
     * @return
     */
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
