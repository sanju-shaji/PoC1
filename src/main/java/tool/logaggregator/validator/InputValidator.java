package tool.logaggregator.validator;

import tool.logaggregator.constants.LogAggregatorToolConstants;
import java.io.File;

/**
 * validation class to validate inputs the user input
 */
public class InputValidator {
    /**
     * menthod to check if user provides a folder path as an argument
     *
     * @param args
     * @return
     */
    private boolean isCommandLineArgumentPresent(String[] args) {
        if (args.length == 0) {
            System.out.println(LogAggregatorToolConstants.NO_COMMAND_LINE_ARGUMENT);
            return false;
        }
        return true;
    }

    /**
     * method to check if the user provided path is a directory or not
     *
     * @param args
     * @return
     */
    private boolean isValidDirectory(String[] args) {
        String userFolderPath = args[0];
        File userFolder = new File(userFolderPath);
        if (!(userFolder.isDirectory())) {
            System.out.println(LogAggregatorToolConstants.INVALID_PATH);
            return false;
        }
        return true;
    }

    /**
     * Method to check if the user input path is empty or not
     *
     * @param args
     * @return
     */
    private boolean isFolderEmpty(String[] args) {
        String userFolderPath = args[0];
        File userFolder = new File(userFolderPath);
        if (userFolder.length() == 0) {
            System.out.println(LogAggregatorToolConstants.EMPTY_FOLDER);
            return false;
        }
        return true;
    }

    /**
     * Method to display the number of valid and invalid file and display processing
     *
     * @param args
     */
    private void validateExtensions(String[] args) {
        String userFolderPath = args[0];
        System.out.println(LogAggregatorToolConstants.PROCESSING);
        File userFolder = new File(userFolderPath);
        String[] folderContents = userFolder.list();
        int logFileCount = 0;
        int nonLogFilesCount = 0;
        long totalFilesCount = folderContents.length;
        for (String files : folderContents) {
            if ((files.endsWith(".log"))) {
                logFileCount += 1;
            }
            nonLogFilesCount = (int) totalFilesCount - logFileCount;
        }
        System.out.println(LogAggregatorToolConstants.TOTAL_FILE_COUNT + totalFilesCount);
        if (nonLogFilesCount != 0) {
            System.out.println(nonLogFilesCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.INVALID_LOG_FILES);
        }
        System.out.println(logFileCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.VALID_LOG_FILES);
    }

    /**
     * validator method to run all the validation
     *
     * @param args
     */
    public void validate(String[] args) {
        if (!isCommandLineArgumentPresent(args)) {
            return;
        }
        try {
            if (isValidDirectory(args) && isFolderEmpty(args)) {
                validateExtensions(args);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}