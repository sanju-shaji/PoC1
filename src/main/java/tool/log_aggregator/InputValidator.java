package tool.log_aggregator;

import java.io.File;
import java.util.Arrays;

import static tool.log_aggregator.LogAggregatorToolConstants.INVALID_LOG_FILES;
import static tool.log_aggregator.LogAggregatorToolConstants.EMPTY_FOLDER;
import static tool.log_aggregator.LogAggregatorToolConstants.PROCESSING;
import static tool.log_aggregator.LogAggregatorToolConstants.INVALID_PATH;
import static tool.log_aggregator.LogAggregatorToolConstants.VALID_LOG_FILES;
import static tool.log_aggregator.LogAggregatorToolConstants.TOTAL_FILE_COUNT;
import static tool.log_aggregator.LogAggregatorToolConstants.SLASH;

public class InputValidator {
    String userFilePath;

    public InputValidator(String userFilePath) {
        this.userFilePath = userFilePath;
    }

    public void validateInput() {
        File userFolder = new File(userFilePath);
        String[] folderContents = userFolder.list();
        if (!(userFolder.isDirectory())) {
            System.out.println(INVALID_PATH);
            return;
        }
        if (folderContents.length == 0) {
            System.out.println(EMPTY_FOLDER);
            return;
        }
        System.out.println(PROCESSING);
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
        System.out.println(TOTAL_FILE_COUNT + totalFilesCount);
        if (noInvalidFlag) {
            System.out.println(nonLogFilesCount + SLASH + totalFilesCount + INVALID_LOG_FILES);
        }
        System.out.println(logFileCount + SLASH + totalFilesCount + VALID_LOG_FILES);
    }
}
