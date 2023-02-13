package core.basesyntax;

public class Line {
    private int value;
    private String name;

    public Line(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + "," + value + System.lineSeparator();
    }
}
