package core.basesyntax;

public class DataRecord {
    private int supply;
    private int buy;

    public int getSupply() {
        return supply;
    }

    public int getBuy() {
        return buy;
    }

    public void addSupply(int amount) {
        supply += amount;
    }

    public void addBuy(int amount) {
        buy += amount;
    }

    public int getResult() {
        return supply - buy;
    }
}
