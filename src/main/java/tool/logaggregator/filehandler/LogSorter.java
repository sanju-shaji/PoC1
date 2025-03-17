package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Comparator;

/**
 * Class for sorting the merged log data
 */
public class LogSorter {

    /**
     * Method to sort all logs based on date and time
     *
     * @return sorted arraylist mergedData
     */
    public ArrayList sortLogFile(ArrayList mergedData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(LogAggregatorToolConstants.SIMPLE_DATE_TIME_PATTERN);
        Collections.sort(mergedData, new Comparator<String>() {
            @Override
            public int compare(String logLineDate1, String logLineDate2) {
                if (logLineDate1.matches(LogAggregatorToolConstants.REGEX_DATETIME_PATTERN) && logLineDate2.matches(LogAggregatorToolConstants.REGEX_DATETIME_PATTERN)) {
                    try {
                        Date date1 = dateFormat.parse(logLineDate1);
                        Date date2 = dateFormat.parse(logLineDate2);
                        return (date1.compareTo(date2));
                    } catch (Exception exception) {
                        exception.getMessage();
                        return 0;
                    }
                }
                return 0;
            }
        });
        return mergedData;
    }
}
