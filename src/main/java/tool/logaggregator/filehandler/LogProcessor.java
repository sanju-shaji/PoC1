package tool.logaggregator.filehandler;

/**
 * logprocessor class for calling logreader,logsorter,logwriter
 */
public class LogProcessor {

    /**
     * call all the logprocessing classes and verify if file processing is succes or not
     *
     * @param args
     */
    public void processFile(String[] args) {
        boolean sucess;
        try {
            LogSorter fileSorter = new LogSorter();
            LogWriter logWriter = new LogWriter();
            fileSorter.sortFile(args);
            String path = null;
            path = logWriter.writeFile(args);

            if (path == null) {
                sucess = false;
                System.out.println("File Processing failed");
            } else {
                sucess = true;
                System.out.println("file processing success");
                System.out.println(path);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
