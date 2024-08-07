package core.basesyntax;

import java.util.List;

public class WorkWithFile {
    private ReaderFromFile readerFromFile;
    private WriteToFile writeToFile;
    private FileProcessor fileProcessor;

    public WorkWithFile() {
        this.writeToFile = new WriteToFile();
        this.readerFromFile = new ReaderFromFile();
        this.fileProcessor = new FileProcessor();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Report report = new Report();
        List<String> stringList = readerFromFile.readFile(fromFileName);
        fileProcessor.processFile(stringList, report);
        writeToFile.writeToFile(report, toFileName);
    }
}
