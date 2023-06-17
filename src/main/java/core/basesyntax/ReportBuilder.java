package core.basesyntax;

public class ReportBuilder {
    public static final String DEFAULT_CSV_ROW_SEPARATOR = System.lineSeparator();
    public static final String DEFAULT_CSV_COLUMN_SEPARATOR = ",";
    public static final String SUPPLY_LABEL = "supply";
    public static final String BUY_LABEL = "buy";
    public static final String RESULT_LABEL = "result";

    public String fromCsvString(String str) {
        if (str == null) {
            throw new RuntimeException("CSV string must not be a null");
        }

        String[] lines = str.split(DEFAULT_CSV_ROW_SEPARATOR);
        StringBuilder stringBuilder = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] columns = line.split(DEFAULT_CSV_COLUMN_SEPARATOR);

            if (columns[0].equals(SUPPLY_LABEL)) {
                supplyTotal += Integer.parseInt(columns[1]);
            }

            if (columns[0].equals(BUY_LABEL)) {
                buyTotal += Integer.parseInt(columns[1]);
            }
        }

        stringBuilder.append(SUPPLY_LABEL)
                .append(DEFAULT_CSV_COLUMN_SEPARATOR)
                .append(supplyTotal)
                .append(DEFAULT_CSV_ROW_SEPARATOR);
        stringBuilder.append(BUY_LABEL)
                .append(DEFAULT_CSV_COLUMN_SEPARATOR)
                .append(buyTotal)
                .append(DEFAULT_CSV_ROW_SEPARATOR);
        stringBuilder.append(RESULT_LABEL)
                .append(DEFAULT_CSV_COLUMN_SEPARATOR)
                .append(supplyTotal - buyTotal);

        return stringBuilder.toString();
    }
}
