package tool.logaggregator.util;

import tool.logaggregator.dao.AuditData;

public class LogAggregatorToolUtil {


    public AuditData setDaoData(String folderPath, int fileCount, String fileNames, String result, String outputFileName, String errorMessage){
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
