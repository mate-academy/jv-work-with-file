package core.basesyntax;

public class ReportCreator {

    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";
    private static final String COMMA_SEPARATOR = ",";

    public String getReport(String[] data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String value : data) {
            if (value.split(COMMA_SEPARATOR)[0].equals(SUPPLY_STRING)) {
                supplyCounter += Integer.parseInt(value.split(COMMA_SEPARATOR)[1]);
            } else if (value.split(COMMA_SEPARATOR)[0].equals(BUY_STRING)) {
                buyCounter += Integer.parseInt(value.split(COMMA_SEPARATOR)[1]);
            }
        }
        return new StringBuilder()
                .append(SUPPLY_STRING)
                .append(COMMA_SEPARATOR)
                .append(supplyCounter)
                .append(System.lineSeparator())
                .append(BUY_STRING)
                .append(COMMA_SEPARATOR)
                .append(buyCounter)
                .append(System.lineSeparator())
                .append(RESULT_STRING)
                .append(COMMA_SEPARATOR)
                .append(supplyCounter - buyCounter)
                .toString();
    }
}
