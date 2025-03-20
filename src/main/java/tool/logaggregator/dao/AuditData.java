package tool.logaggregator.dao;

import lombok.*;

/**
 * Entity class for variables used for auditting data in database
 */
@Data
public class AuditData {
    private String folderPath;
    private int fileCount;
    private String fileNames;
    private String result;
    private String outputFileName;
    private String errorMessage;
}
