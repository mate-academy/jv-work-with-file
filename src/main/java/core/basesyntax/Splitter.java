package core.basesyntax;

public class Splitter {
    private static final String COMMA = ",";

    public ReportItem splitString(String string) {
        String[] stringArray = string.split(COMMA);
        if (stringArray.length < 2) {
            throw new RuntimeException("Input string has invalid format");
        }
        boolean isBuy = OperationType.BUY.name().equalsIgnoreCase(stringArray[0]);
        OperationType operationType = isBuy ? OperationType.BUY : OperationType.SUPPLY;
        return new ReportItem(operationType, Integer.parseInt(stringArray[1]));
    }
}
