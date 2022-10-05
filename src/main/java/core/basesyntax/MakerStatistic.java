package core.basesyntax;

import java.util.List;

public class MakerStatistic {
    private static final String[] NAME_FIELDS = {"supply", "buy", "result"};
    private static final int INDEX_SUPPLY = 0;
    private static final int INDEX_BUY = 1;
    private static final int INDEX_RECORD = 2;
    private static final char SPACER = ',';
    private static final int INDEX_NAME_IN_RECORD = 0;
    private static final int INDEX_AMOUNT_IN_RECORD = 1;
    private StringBuilder stringBuilder = new StringBuilder();

    private String makeRecord(int amount, int numberField) {
        stringBuilder.append(NAME_FIELDS[numberField])
                .append(SPACER)
                .append(amount);
        if (numberField != 2) {
            stringBuilder.append(System.lineSeparator());
        }
        String record = stringBuilder.toString();
        stringBuilder.setLength(0);
        return record;
    }

    public String[] makeStatistic(List<String> records) {
        String[] statistic = new String[3];

        int amountSupplied = 0;
        int amountBought = 0;
        for (String record : records) {
            String[] value = record.split(",");
            if (value[INDEX_NAME_IN_RECORD].equals(NAME_FIELDS[INDEX_SUPPLY])) {
                amountSupplied += Integer.valueOf(value[INDEX_AMOUNT_IN_RECORD]);
            } else if (value[INDEX_NAME_IN_RECORD].equals(NAME_FIELDS[INDEX_BUY])) {
                amountBought += Integer.valueOf(value[INDEX_AMOUNT_IN_RECORD]);
            }
        }
        statistic[INDEX_SUPPLY] = makeRecord(amountSupplied, INDEX_SUPPLY);
        statistic[INDEX_BUY] = makeRecord(amountBought, INDEX_BUY);
        statistic[INDEX_RECORD] = makeRecord(amountSupplied - amountBought, INDEX_RECORD);
        return statistic;
    }
}
