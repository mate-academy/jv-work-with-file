package core.basesyntax;

public class DataProcessing {
    private static final int INPUT_DATA_KEY = 0;
    private static final int INPUT_DATA_VALUE = 1;
    private static final int DATA_INDEX_SUPPLY = 0;
    private static final int DATA_INDEX_BUY = 1;
    private static final int DATA_INDEX_RESULT = 2;
    private static final String[] NAMES = new String[]{"supply", "buy", "result"};
    private final int[] values = new int[]{0, 0};

    public String createReport(String sourceData) {
        if (sourceData == null || sourceData.isEmpty()) {
            return "";
        }

        for (String line : sourceData.split(System.lineSeparator())) {
            int amount;
            String[] row = line.split(",");

            try {
                amount = Integer.parseInt(row[INPUT_DATA_VALUE]);
            } catch (NumberFormatException e) {
                continue;
            }

            if (NAMES[DATA_INDEX_SUPPLY].equals(row[INPUT_DATA_KEY])) {
                values[DATA_INDEX_SUPPLY] += amount;
            } else if (NAMES[DATA_INDEX_BUY].equals(row[INPUT_DATA_KEY])) {
                values[DATA_INDEX_BUY] += amount;
            }
        }
        return getResult();
    }

    private String getResult() {
        return String.format("%s,%d%s",
                NAMES[DATA_INDEX_SUPPLY], values[DATA_INDEX_SUPPLY], System.lineSeparator())
                + String.format("%s,%d%s",
                NAMES[DATA_INDEX_BUY], values[DATA_INDEX_BUY], System.lineSeparator())
                + String.format("%s,%d",
                NAMES[DATA_INDEX_RESULT], values[DATA_INDEX_SUPPLY] - values[DATA_INDEX_BUY]);
    }
}
