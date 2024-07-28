package core.basesyntax;

public class Splitter {
    public ReportItem splitString(String string) {
        String[] stringArray = string.split(",");
        Name name = Name.BUY.name().equalsIgnoreCase(stringArray[0]) ? Name.BUY : Name.SUPPLY;
        return new ReportItem(name, Integer.parseInt(stringArray[1]));
    }
}
