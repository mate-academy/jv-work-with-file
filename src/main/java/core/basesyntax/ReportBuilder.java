package core.basesyntax;

public class ReportBuilder {
    public static final String DEFAULT_CSV_ROW_SEPARATOR = System.lineSeparator();
    public static final String DEFAULT_CSV_COLUMN_SEPARATOR = ",";
    public static final byte LABEL_INDEX = 0;
    public static final byte VALUE_INDEX = 1;
    public static final String SUPPLY_LABEL = "supply";
    public static final String BUY_LABEL = "buy";
    public static final String RESULT_LABEL = "result";

    public String fromCsvString(String csvString) {
        if (csvString == null) {
            throw new RuntimeException("CSV string must not be a null");
        }

        String[] csvLines = csvString.split(DEFAULT_CSV_ROW_SEPARATOR);
        StringBuilder reportContentBuilder = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String csvLine : csvLines) {
            String[] csvColumn = csvLine.split(DEFAULT_CSV_COLUMN_SEPARATOR);

            if (csvColumn[LABEL_INDEX].equals(SUPPLY_LABEL)) {
                supplyTotal += Integer.parseInt(csvColumn[VALUE_INDEX]);
            }

            if (csvColumn[LABEL_INDEX].equals(BUY_LABEL)) {
                buyTotal += Integer.parseInt(csvColumn[VALUE_INDEX]);
            }
        }

        reportContentBuilder.append(SUPPLY_LABEL)
                .append(DEFAULT_CSV_COLUMN_SEPARATOR)
                .append(supplyTotal)
                .append(DEFAULT_CSV_ROW_SEPARATOR);
        reportContentBuilder.append(BUY_LABEL)
                .append(DEFAULT_CSV_COLUMN_SEPARATOR)
                .append(buyTotal)
                .append(DEFAULT_CSV_ROW_SEPARATOR);
        reportContentBuilder.append(RESULT_LABEL)
                .append(DEFAULT_CSV_COLUMN_SEPARATOR)
                .append(supplyTotal - buyTotal);

        return reportContentBuilder.toString();
    }
}
