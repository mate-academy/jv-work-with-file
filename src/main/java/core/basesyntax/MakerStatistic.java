package core.basesyntax;

import java.util.List;

public class MakerStatistic {
    private static final String[] NAME_FIELDS = {"supply", "buy", "result"};
    private static final int INDEX_SUPPLY = 0;
    private static final int INDEX_BUY = 1;
    private static final int INDEX_RECORD = 2;

    private String makeRecord(int amount, int numberField) {
        StringBuilder stringBuilder = new StringBuilder();
        char spacer = ',';
        stringBuilder.append(NAME_FIELDS[numberField])
                .append(spacer)
                .append(amount);
        if (numberField != INDEX_RECORD) {
            stringBuilder.append(System.lineSeparator());
        }
        String record = stringBuilder.toString();
        stringBuilder.setLength(0);
        return record;
    }

    public String[] makeStatistic(List<String> records) {
        int indexNameInRecord = 0;
        int indexAmountInRecord = 1;
        String[] statistic = new String[3];
        int amountSupplied = 0;
        int amountBought = 0;
        for (String record : records) {
            String[] value = record.split(",");
            if (value[indexNameInRecord].equals(NAME_FIELDS[INDEX_SUPPLY])) {
                amountSupplied += Integer.valueOf(value[indexAmountInRecord]);
            } else if (value[indexNameInRecord].equals(NAME_FIELDS[INDEX_BUY])) {
                amountBought += Integer.valueOf(value[indexAmountInRecord]);
            }
        }
        statistic[INDEX_SUPPLY] = makeRecord(amountSupplied, INDEX_SUPPLY);
        statistic[INDEX_BUY] = makeRecord(amountBought, INDEX_BUY);
        statistic[INDEX_RECORD] = makeRecord(amountSupplied - amountBought, INDEX_RECORD);
        return statistic;
    }
}
