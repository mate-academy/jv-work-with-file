package core.basesyntax;

public class Table {
    private int buy;
    private int supply;
    private int result;

    public Table(int buy, int supply) {
        this.buy = buy;
        this.supply = supply;
        setResult(buy, supply);
    }

    public int getBuy() {
        return buy;
    }

    public int getSupply() {
        return supply;
    }

    public int getResult() {
        return result;
    }

    private void setResult(int buy, int supply) {
        this.result = supply - buy;
    }

}
