package core.basesyntax;

public class Data {
    private int value;
    private String type;

    public Data(String type) {
        this.type = type;
    }

    public Data(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void addValue(int value) {
        this.value += value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + "," + value;
    }
}
