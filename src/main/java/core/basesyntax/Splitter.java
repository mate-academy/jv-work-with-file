package core.basesyntax;

public class Splitter {
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int ITEM_ARRAY_LENGTH = 2;

    public ReportItem splitString(String string) {
        String[] stringArray = string.split(COMMA);
        if (stringArray.length < ITEM_ARRAY_LENGTH) {
            throw new RuntimeException("Input string has invalid format");
        }
        boolean isBuy = OperationType.BUY.name().equalsIgnoreCase(stringArray[OPERATION_INDEX]);
        OperationType operationType = isBuy ? OperationType.BUY : OperationType.SUPPLY;
        return new ReportItem(operationType, Integer.parseInt(stringArray[VALUE_INDEX]));
    }
}
