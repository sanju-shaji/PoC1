package tool.logaggregator;

import tool.logaggregator.audit.LogaggregatortoolAudit;
import tool.logaggregator.filehandler.LogFileProcessor;
import tool.logaggregator.validator.InputValidator;

public class LogAggregatorTool {
    /**
     * main method
     */
    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator();
        LogFileProcessor logFileProcessor = new LogFileProcessor();
        LogaggregatortoolAudit logaggregatortoolAudit=new LogaggregatortoolAudit();
        if (inputValidator.validate(args)) {
            logFileProcessor.processLogFile(args);
            logaggregatortoolAudit.addAudit(args);
        }
    }
}
