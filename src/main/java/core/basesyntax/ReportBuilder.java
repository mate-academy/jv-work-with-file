package core.basesyntax;

public class ReportBuilder {

    public String buildReport(int supply, int buy, int result) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }
}
