package core.basesyntax;

public class WorkWithFile {
    private final FileContentReader fileContentReader = new FileContentReader();
    private final ReportBuilder reportBuilder = new ReportBuilder();
    private final FileContentWriter fileContentWriter = new FileContentWriter();

    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null || toFileName == null) {
            return;
        }

        String content = fileContentReader.read(fromFileName);
        String resultContent = reportBuilder.fromCsvString(content);
        fileContentWriter.write(toFileName, resultContent);
    }
}
