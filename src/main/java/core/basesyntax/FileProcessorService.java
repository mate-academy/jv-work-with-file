package core.basesyntax;

import java.util.List;

public class FileProcessorService {
    private Splitter splitter;
    private Calculator calculator;

    public FileProcessorService() {
        this.splitter = new Splitter();
        this.calculator = new Calculator();
    }

    public void processFile(List<String> processStrings, Report report) {
        for (String str : processStrings) {
            ReportItem reportItem = splitter.splitString(str);
            calculator.calculateReportItems(report, reportItem);
        }
    }
}
