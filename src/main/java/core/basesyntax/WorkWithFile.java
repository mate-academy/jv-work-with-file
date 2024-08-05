package core.basesyntax;

import java.util.List;

public class WorkWithFile {
    private ReaderFromFile readerFromFile;
    private EntryToFile entryToFile;
    private FileProcessor fileProcessor;

    public WorkWithFile() {
        this.entryToFile = new EntryToFile();
        this.readerFromFile = new ReaderFromFile();
        this.fileProcessor = new FileProcessor();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Report report = new Report();
        List<String> stringList = readerFromFile.readFile(fromFileName);
        fileProcessor.processFile(stringList, report);
        entryToFile.writeToFile(report, toFileName);
    }
}
