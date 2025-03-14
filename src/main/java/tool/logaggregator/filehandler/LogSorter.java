package tool.logaggregator.filehandler;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class for sorting the merged log data
 */
public class LogSorter {

    /**
     * Method to sort all logs based on date and time
     *
     * @param args
     * @return sorted arraylist fileData
     */
    public ArrayList sortFile(String[] args) {
        LogReader logReader = new LogReader();
        ArrayList<String> fileData;
        fileData = logReader.readData(args);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:sss");
        Collections.sort(fileData, new Comparator<String>() {
            @Override
            public int compare(String line1, String line2) {
                if (line1.matches("\\d{4}-\\d{2}-\\d{2}") && line2.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    try {
                        Date date1 = dateFormat.parse(line1);
                        Date date2 = dateFormat.parse(line2);
                        return (date1.compareTo(date2));
                    } catch (Exception e) {
                        return 0;
                    }
                }
                return 0;
            }
        });
        return fileData;
    }
}
