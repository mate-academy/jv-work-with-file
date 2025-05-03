package core.basesyntax;

public class WorkWithFile {
    private final FileContentReader csvContentReader = new FileContentReader();
    private final ReportBuilder csvReportBuilder = new ReportBuilder();
    private final FileContentWriter csvContentWriter = new FileContentWriter();

    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null) {
            throw new RuntimeException("Initial report file path must not be a null");
        }

        if (toFileName == null) {
            throw new RuntimeException("Result report file path must not be a null");
        }

        String initialReportContent = csvContentReader.read(fromFileName);
        String resultContent = csvReportBuilder.fromCsvString(initialReportContent);
        csvContentWriter.write(toFileName, resultContent);
    }
}
