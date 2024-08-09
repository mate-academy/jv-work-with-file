package core.basesyntax;

import java.util.List;

public class WorkWithFile {
    private FileReaderService readerFromFile;
    private FileWriterService writeToFile;
    private FileProcessorService fileProcessor;

    public WorkWithFile() {
        this.writeToFile = new FileWriterService();
        this.readerFromFile = new FileReaderService();
        this.fileProcessor = new FileProcessorService();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Report report = new Report();
        List<String> stringList = readerFromFile.readFile(fromFileName);
        fileProcessor.processFile(stringList, report);
        writeToFile.writeToFile(report, toFileName);
    }
}
