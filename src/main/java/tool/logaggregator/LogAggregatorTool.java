package tool.logaggregator;

import tool.logaggregator.validator.InputValidator;


public class LogAggregatorTool {
    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {
        InputValidator validator = new InputValidator();
        validator.validate(args);
    }
}