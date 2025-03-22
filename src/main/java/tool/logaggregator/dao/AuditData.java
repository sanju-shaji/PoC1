package tool.logaggregator.dao;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for variables used for auditting data in database
 */
@Getter
@Setter
public class AuditData {
    private String folderPath;
    private int fileCount;
    private String fileNames;
    private String result;
    private String outputFileName;
    private String errorMessage;
}
