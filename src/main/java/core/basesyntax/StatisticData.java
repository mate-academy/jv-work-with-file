package core.basesyntax;

public class StatisticData {

    private String operationType;
    private int value;

    public StatisticData(String type, int value) {
        this.operationType = type;
        this.value = value;
    }

    public void setOperationType(String type) {
        this.operationType = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getOperationType() {
        return operationType;
    }

    public int getValue() {
        return value;
    }
}
