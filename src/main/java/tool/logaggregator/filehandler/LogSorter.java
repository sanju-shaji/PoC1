package tool.logaggregator.filehandler;

import tool.logaggregator.constants.LogAggregatorToolConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for sorting the merged log data
 */
public class LogSorter {
    private static final Pattern yearMonthDayPattern = Pattern.compile(LogAggregatorToolConstants.REGEX_DATETIME_PATTERN_YMD);
    private static final Pattern monthDayYearPattern = Pattern.compile(LogAggregatorToolConstants.REGEX_DATETIME_PATTERN_MDY);

    /**
     * method to extract the datetime from loglines using regular expression and simpledatetime format
     *
     * @param logLine
     * @return
     * @throws ParseException
     */
    private Date extractDate(String logLine) throws ParseException {
        SimpleDateFormat yearMonthDayFormat = new SimpleDateFormat(LogAggregatorToolConstants.SIMPLE_DATE_TIME_PATTERN_YMD);
        SimpleDateFormat monthDayYearFormat = new SimpleDateFormat(LogAggregatorToolConstants.SIMPLE_DATE_TIME_PATTERN_MDY);
        Matcher yearMonthDayFormatMatcher = yearMonthDayPattern.matcher(logLine);
        Matcher monthDayYearFormatMatcher = monthDayYearPattern.matcher(logLine);
        if (yearMonthDayFormatMatcher.find()) {
            return yearMonthDayFormat.parse(yearMonthDayFormatMatcher.group(1));
        } else if (monthDayYearFormatMatcher.find()) {
            return monthDayYearFormat.parse(monthDayYearFormatMatcher.group(1));
        } else {
            throw new ParseException("No valid date in logline" + logLine, 0);
        }
    }

    /**
     * Method to sort all logs based on date and time
     *
     * @return sorted arraylist mergedData
     */
    public ArrayList sortLogFile(ArrayList mergedData) {
        ArrayList<String> validLogs = new ArrayList<>();
        for (Object log : mergedData) {
            try {
                extractDate((String) log);
                validLogs.add((String) log);
            } catch (ParseException ignored) {
            }
        }
        Collections.sort(validLogs, new Comparator<String>() {
            @Override
            public int compare(String logLineDate1, String logLineDate2) {
                try {
                    Date date1 = extractDate(logLineDate1);
                    Date date2 = extractDate(logLineDate2);
                    return (date1.compareTo(date2));
                } catch (ParseException exception) {
                    System.out.println(exception.getMessage());
                    return 0;
                }
            }
        });
        return validLogs;
    }
}
