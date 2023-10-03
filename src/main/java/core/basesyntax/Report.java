package core.basesyntax;

public class Report {
    private static final String DATA_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT = "result";
    private final int  supply;
    private final int buy;

    public Report(int supply, int buy) {
        this.supply = supply;
        this.buy = buy;
    }

    public String createReportString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_OPERATION).append(DATA_SEPARATOR).append(supply)
                .append(System.lineSeparator());
        stringBuilder.append(BUY_OPERATION).append(DATA_SEPARATOR).append(buy)
                .append(System.lineSeparator());
        stringBuilder.append(RESULT).append(DATA_SEPARATOR).append(supply - buy);
        return stringBuilder.toString();
    }
}
