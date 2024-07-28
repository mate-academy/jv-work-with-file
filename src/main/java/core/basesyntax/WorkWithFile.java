package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    private Splitter splitter;
    private Calculator calculator;
    private EntryToFile entryToFile;

    public WorkWithFile() {
        this.splitter = new Splitter();
        this.calculator = new Calculator();
        this.entryToFile = new EntryToFile();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Report report = new Report();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                ReportItem reportItem = splitter.splitString(value);
                calculator.calculateStatistics(report, reportItem);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read or write to file");
        }
        entryToFile.writeToFile(report, toFileName);
    }
}
