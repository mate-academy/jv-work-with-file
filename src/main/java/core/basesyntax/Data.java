package core.basesyntax;

public class Data {
    private int value;
    private TypeEnum type;

    public Data(TypeEnum type) {
        this.type = type;
    }

    public Data(int value, TypeEnum type) {
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

    public TypeEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString().toLowerCase() + "," + value;
    }
}
