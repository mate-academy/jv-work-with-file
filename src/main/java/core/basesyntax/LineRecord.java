package core.basesyntax;

public class LineRecord {
    private final String operatorType;
    private int amount;

    public LineRecord(String operatorType, int amount) {
        this.operatorType = operatorType;
        this.amount = amount;
    }

    public String getLineOperatorTypeName() {
        return operatorType;
    }

    public int getLineAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public String getConcatenatedLine() {
        return getLineOperatorTypeName() + "," + getLineAmount();
    }
}
