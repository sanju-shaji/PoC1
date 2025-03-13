package tool.logaggregator;

import tool.logaggregator.validator.InputValidator;

public class LogAggregatorTool {
    /**
     * main method
     */
    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator();
        inputValidator.validate(args);
    }
}
