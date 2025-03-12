package tool.logaggregator;

import tool.logaggregator.validator.InputValidator;

import java.io.File;

public class LogAggregatorTool {
    public static void main(String[] args) {
        String userFolderPath = args[0];
        File userFolder = new File(userFolderPath);
        InputValidator validator = new InputValidator(userFolderPath);
        try {
            if (validator.checkValidDirectory(userFolder) && validator.checkEmpty(userFolder)) {
                validator.processingFileDetails();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
