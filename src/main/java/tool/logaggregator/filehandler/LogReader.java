package tool.logaggregator.filehandler;

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
     * @param args :commandline argument
     * @return arraylist filedata which contains all the merged data
     */
    public ArrayList readData(String[] args) {
        String userFilePath = args[0];
        ArrayList<String> fileData = new ArrayList<>();
        File userFolder = new File(userFilePath);
        String[] folderContents = userFolder.list();
        try {
            for (String fileName : folderContents) {
                Reader fileReader = new java.io.FileReader(userFilePath + "\\" + fileName);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileData.add(line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fileData;
    }
}
