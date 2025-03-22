package tool.logaggregator.util;

import tool.logaggregator.dao.AuditData;

/**
 * Utility class for setting DAO
 */
public class LogAggregatorToolUtil {
    /**
     * Method for setting all the dao data for auditting in DB
     *
     * @return
     */
    public AuditData buildAuditData(String folderPath, int fileCount, String fileNames, String result, String outputFileName, String errorMessage) {
        AuditData auditData = new AuditData();
        auditData.setFolderPath(folderPath);
        auditData.setFileCount(fileCount);
        auditData.setFileNames(fileNames);
        auditData.setResult(result);
        auditData.setOutputFileName(outputFileName);
        auditData.setErrorMessage(errorMessage);
        return auditData;
    }
}
