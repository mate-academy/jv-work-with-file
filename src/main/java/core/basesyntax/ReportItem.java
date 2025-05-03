package core.basesyntax;

public class ReportItem {
    private OperationType operationType;
    private int value;

    public ReportItem(OperationType operationType, int value) {
        this.operationType = operationType;
        this.value = value;
    }

    public OperationType getOperationType() {
        return this.operationType;
    }

    public int getValue() {
        return this.value;
    }
}
