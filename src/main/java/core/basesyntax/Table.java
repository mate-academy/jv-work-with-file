package core.basesyntax;

public class Table {
    private int buy;
    private int supply;

    public Table(int buy, int supply) {
        this.buy = buy;
        this.supply = supply;
    }

    public int getBuy() {
        return buy;
    }

    public int getSupply() {
        return supply;
    }

    public int calculateResult() {
        return supply - buy;
    }
}
