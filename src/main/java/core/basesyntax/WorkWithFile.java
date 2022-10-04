package core.basesyntax;

public class WorkWithFile {
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void getStatistic(String fromFileName, String toFileName) {
        writer.writeReportToFile(toFileName, reader.readFromFile(fromFileName));
    }
}
