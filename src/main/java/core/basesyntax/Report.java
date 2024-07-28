package core.basesyntax;

public class Report {

    private int buys;
    private int supplies;

    public int getBuys() {
        return buys;
    }

    public int getSupplies() {
        return supplies;
    }

    public void addBuy(int value) {
        buys += value;
    }

    public void addSupply(int value) {
        supplies += value;
    }

    public int getResult() {
        return supplies - buys;
    }
}
