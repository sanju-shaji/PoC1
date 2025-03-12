package tool.logaggregator.validator;

import tool.logaggregator.constants.LogAggregatorToolConstants;

import java.io.File;

//To validate the folder path which the user inputs
public class InputValidator {
    public String userFolderPath;

    public InputValidator(String userFolderPath) {
        this.userFolderPath = userFolderPath;
    }

    //  To check if the user input path is a valid directory
    public boolean checkValidDirectory(File userFolder) {
        if (!(userFolder.isDirectory())) {
            System.out.println(LogAggregatorToolConstants.INVALID_PATH);
            return false;
        }
        return true;
    }

    //    To check if the user input path is empty or not
    public boolean checkEmpty(File userFolder) {
        if (userFolder.length() == 0) {
            System.out.println(LogAggregatorToolConstants.EMPTY_FOLDER);
            return false;
        }
        return true;
    }

    //  To display the number of valid and invalid file and display processing
    public void processingFileDetails() {
        System.out.println(LogAggregatorToolConstants.PROCESSING);
        File userFolder = new File(userFolderPath);
        String[] folderContents = userFolder.list();
        int logFileCount = 0;
        int nonLogFilesCount = 0;
        boolean noInvalidFlag = false;
        long totalFilesCount = folderContents.length;
        for (String files : folderContents) {
            if ((files.endsWith(".log"))) {
                logFileCount += 1;
            }
            if (!(files.endsWith(".log"))) {
                nonLogFilesCount += 1;
                noInvalidFlag = true;
            }
        }
        System.out.println(LogAggregatorToolConstants.TOTAL_FILE_COUNT + totalFilesCount);
        if (noInvalidFlag) {
            System.out.println(nonLogFilesCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.INVALID_LOG_FILES);
        }
        System.out.println(logFileCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.VALID_LOG_FILES);
    }
}