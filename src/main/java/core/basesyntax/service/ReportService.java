package core.basesyntax.service;

public class ReportService {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public String generateReport(int[] salesVolume) {
        int supply = salesVolume[SUPPLY_INDEX];
        int buy = salesVolume[BUY_INDEX];
        int result = salesVolume[RESULT_INDEX];

        StringBuilder report = new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).append(System.lineSeparator());

        return report.toString();
    }
}
