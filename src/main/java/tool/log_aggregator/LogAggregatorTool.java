package tool.log_aggregator;

public class LogAggregatorTool {
    public static void main(String[] args) {
        String userFilePath = args[0];
        InputValidator validator = new InputValidator(userFilePath);
        validator.validateInput();
    }
}
