package core.basesyntax;

public class Data {
    private Integer value = 0;
    private String type = null;

    public Data(String type) {
        this.type = type;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Data(Integer value, String type) {
        this.value = value;
        this.type = type;
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
