package tool.logaggregator.constants;

/**
 * class to hold all the string constants
 */
public class LogAggregatorToolConstants {
    public static final String ADD_AUDIT_QUERY = "INSERT INTO audit(folder_path, file_count, file_names, operation_datetime, result, output_file_name, error_message) VALUES(?, ?, ?, NOW(), ?, ?, ?)";
    public static final String ARRAY_TO_STRING_DELIMITER = ",  ";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/logaggregatoraudit";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Abc@1234";
    public static final String EMPTY_FOLDER = "Folder is empty";
    public static final String FILE_PROCESSING_FAILED = "File Processing failed";
    public static final String FILE_PROCESSING_SUCCESS = "File Processing success";
    public static final String FILE_NAME_DATETIME_FORMAT = "yyyy-MM-dd_HH_mm_ss";
    public static final String INVALID_LOG_FILES = " are invalid files";
    public static final String INVALID_PATH = "Invalid Folder Path. Please Provide a valid folder path";
    public static final String LOG_EXTENSION = ".log";
    public static final String NEW_LINE = "\n";
    public static final String NO_COMMAND_LINE_ARGUMENT = "Please provide folderpath as argument ";
    public static final String OUTPUT_DIRECTORY = "D:\\Internship\\SortedFiles";
    public static final String PROCESSING = "Processing...";
    public static final String PROCESS_SUCCESS = "Sucess";
    public static final String PROCESS_FAILED = "Failed";
    public static final String REGEX_DATETIME_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    public static final String SIMPLE_DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss:sss";
    public static final String SLASH = "\\";
    public static final String SORTED_FILE_PATH = "Sorted File Path : ";
    public static final String SORTED_FILE_NAME = "\\LogAggegator_Merged_File ";
    public static final String TOTAL_FILE_COUNT = "Total number file in the specified Folder : ";
    public static final String VALID_LOG_FILES = " are valid files";
}
