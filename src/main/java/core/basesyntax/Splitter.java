package core.basesyntax;

public class Splitter {
    private static final String COMMA = ",";

    public ReportItem splitString(String string) {
        String[] stringArray = string.split(COMMA);
        if (stringArray.length < 2) {
            throw new RuntimeException("Input string has invalid format");
        }
        boolean isBuy = Name.BUY.name().equalsIgnoreCase(stringArray[0]);
        Name name = isBuy ? Name.BUY : Name.SUPPLY;
        return new ReportItem(name, Integer.parseInt(stringArray[1]));
    }
}
