package core.basesyntax;

public class Report {
    private static final String DATA_SEPARATOR = ",";
    private final int supply;
    private final int buy;

    public Report(int supply, int buy) {
        this.supply = supply;
        this.buy = buy;
    }

    public String createReportString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply").append(DATA_SEPARATOR).append(supply).append(System.lineSeparator());
        stringBuilder.append("buy").append(DATA_SEPARATOR).append(buy).append(System.lineSeparator());
        stringBuilder.append("result").append(DATA_SEPARATOR).append(supply - buy);
        return stringBuilder.toString();
    }
}
