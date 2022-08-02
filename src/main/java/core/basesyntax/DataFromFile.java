package core.basesyntax;

public class DataFromFile {
    private String operationType;
    private int amount;

    public DataFromFile(String operationType, int amount) {
        this.operationType = operationType;
        this.amount = amount;
    }

    public String getOperationType() {
        return operationType;
    }

    public int getAmount() {
        return amount;
    }
}
