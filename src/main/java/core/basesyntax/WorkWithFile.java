package core.basesyntax;

public class WorkWithFile {
    private static final FileService fileService = new FileService();
    private static final ReportBuilder reportBuilder = new ReportBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        fileService.writeLinesToFile(
                reportBuilder.buildReport(fileService.readLinesFromFile(fromFileName)),
                toFileName);
    }
}
