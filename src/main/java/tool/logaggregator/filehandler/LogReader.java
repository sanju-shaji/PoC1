package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.util.ArrayList;

/**
 * class for reading the log file data
 * reads each line of logfile and store it to Arraylist
 */
public class LogReader {


    /**
     * method for reading and merging all the log files
     *
     * @return arraylist filedata which contains all the merged data
     */
    public ArrayList readLogData(String userFilePath) {

        ArrayList<String> fileData = new ArrayList<>();
        File userFolder = new File(userFilePath);
        String[] folderContents = userFolder.list();
        try {
            for (String fileName : folderContents) {
                Reader fileReader = new java.io.FileReader(userFilePath + LogAggregatorToolConstants.SLASH + fileName);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileData.add(line);
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return fileData;
    }
}
