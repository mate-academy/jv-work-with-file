package core.basesyntax;

public class StatisticsElement {

    private String name;
    private int sum;

    public StatisticsElement(String name) {
        this.name = name;
        this.sum = 0;
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void addToSum(int sum) {
        this.sum = this.sum + sum;
    }

    @Override
    public String toString() {
        return "StatisticsElement{"
                + "name='" + name + '\''
                + ", sum=" + sum + '}';
    }
}
