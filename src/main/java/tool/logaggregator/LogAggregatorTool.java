package tool.logaggregator;

import tool.logaggregator.filehandler.LogFileProcessor;
import tool.logaggregator.validator.InputValidator;

public class LogAggregatorTool {
    /**
     * main method
     */
    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator();
        LogFileProcessor logFileProcessor = new LogFileProcessor();
        if (inputValidator.validateUserInput(args)) {
            logFileProcessor.processLogFile(args);
        }
    }
}
