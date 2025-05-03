package core.basesyntax.models;

public class Operation {
    private static final int OPERATION_TYPE_ARRAY_POSITION = 0;
    private static final int AMOUNT_ARRAY_POSITION = 1;
    private String operationType;
    private int amount;

    public Operation(String[] operation) {
        this.operationType = operation[OPERATION_TYPE_ARRAY_POSITION];
        this.amount = Integer.parseInt(operation[AMOUNT_ARRAY_POSITION]);
    }

    public String getOperationType() {
        return operationType;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Operation{"
                + "operationType='" + operationType + '\''
                + ", amount=" + amount
                + '}';
    }
}
