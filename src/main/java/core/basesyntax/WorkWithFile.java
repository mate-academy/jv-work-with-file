package core.basesyntax;

import java.util.List;

public class WorkWithFile {
    private final FileParser fileParser = new FileParser();
    private final ReportGenerator reportGenerator = new ReportGenerator();
    private final WriterToFile writerToFile = new WriterToFile();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> content = fileParser.parseFileContent(fromFileName);
        int[] calculatedValues = reportGenerator.calculateValues(content);
        String resultContent = reportGenerator.generateReport(calculatedValues);
        writerToFile.writeToFile(resultContent, toFileName);
    }
}
