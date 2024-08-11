package core.basesyntax.model;

public class Operation {
    private OperationType operationType;
    private int amount;

    public Operation(OperationType operationType, int amount) {
        this.operationType = operationType;
        this.amount = amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public int getAmount() {
        return amount;
    }
}
