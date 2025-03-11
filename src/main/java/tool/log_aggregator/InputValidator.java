package tool.log_aggregator;

import java.io.File;
import java.util.Arrays;

public class InputValidator {
    String userFilePath;

    public InputValidator(String userFilePath) {
        this.userFilePath = userFilePath;
    }

    public void validateInput() {
        File userFolder = new File(userFilePath);
        String[] folderContents = userFolder.list();
        if (!(userFolder.isDirectory())) {
            System.out.println(LogAggregatorToolConstants.INVALID_PATH);
            return;
        }
        if (folderContents.length == 0) {
            System.out.println(LogAggregatorToolConstants.EMPTY_FOLDER);
            return;
        }
        System.out.println(LogAggregatorToolConstants.PROCESSING);
        int logFileCount = 0;
        int nonLogFilesCount = 0;
        boolean noInvalidFlag = false;
        long totalFilesCount = (Arrays.stream(folderContents).count());
        for (String files : folderContents) {
            if ((files.endsWith(".log"))) {
                logFileCount += 1;
            } else {
                if (!(files.endsWith(".log"))) {
                    nonLogFilesCount += 1;
                    noInvalidFlag = true;
                }
            }
        }
        System.out.println(LogAggregatorToolConstants.TOTAL_FILE_COUNT + totalFilesCount);
        if (noInvalidFlag) {
            System.out.println(nonLogFilesCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.INVALID_LOG_FILES);
        }
        System.out.println(logFileCount + LogAggregatorToolConstants.SLASH + totalFilesCount + LogAggregatorToolConstants.VALID_LOG_FILES);
    }
}
