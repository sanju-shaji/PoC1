package tool.logaggregator;

import tool.logaggregator.filehandler.LogProcessor;
import tool.logaggregator.validator.InputValidator;

public class LogAggregatorTool {
    /**
     * main method
     */
    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator();
        LogProcessor logProcessor = new LogProcessor();
        inputValidator.validate(args);
        logProcessor.processFile(args);
    }
}
