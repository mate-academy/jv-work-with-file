package core.basesyntax;

public class WorkWithFile {
    private Reader reader = new Reader();
    private ReportFromFile report = new ReportFromFile();
    private Writer writer = new Writer();

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = reader.reade(fromFileName);
        int[] amount = report.reportedFromFile(stringBuilder);
        writer.write(toFileName, amount);
    }
}
