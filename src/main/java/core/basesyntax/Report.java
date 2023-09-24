package core.basesyntax;

public class Report {
    private  final int supply;
    private  final int buy;
    private static final String DATA_SEPARATOR = ",";

    public Report(int supply, int buy) {
        this.supply = supply;
        this.buy = buy;
    }

    public String createReportString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply").append(DATA_SEPARATOR).append(supply).append('\n');
        stringBuilder.append("buy").append(DATA_SEPARATOR).append(buy).append('\n');
        stringBuilder.append("result").append(DATA_SEPARATOR).append(supply-buy);
        return stringBuilder.toString();
    }
}
