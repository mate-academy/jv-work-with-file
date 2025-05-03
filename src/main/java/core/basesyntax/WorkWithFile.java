package core.basesyntax;

import java.util.Collection;

public class WorkWithFile {
    private final FileService fileService = new FileService();
    private final ReportBuilder reportBuilder = new ReportBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        Collection<String> inputData = fileService.readLinesFromFile(fromFileName);
        Collection<String> report = reportBuilder.buildReport(inputData);
        fileService.writeLinesToFile(report, toFileName);
    }
}
