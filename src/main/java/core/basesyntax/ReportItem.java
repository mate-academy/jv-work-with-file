package core.basesyntax;

public class ReportItem {
    private Name name;
    private int value;

    public ReportItem(Name name, int value) {
        this.name = name;
        this.value = value;
    }

    public Name getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}
