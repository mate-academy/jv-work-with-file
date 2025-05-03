package core.basesyntax;

import java.util.List;

public class WorkWithFile {
    private final ReaderFile readerFile = new ReaderFile();
    private final ReportService reportService = new ReportService();
    private final WriterToFile writerToFile = new WriterToFile();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> content = readerFile.readFromFile(fromFileName);
        int[] calculatedValues = reportService.calculateValues(content);
        String resultContent = reportService.generateReport(calculatedValues);
        writerToFile.writeToFile(resultContent, toFileName);
    }
}
