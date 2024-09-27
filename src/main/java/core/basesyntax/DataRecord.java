package core.basesyntax;

public class DataRecord {
    private int supply;
    private int buy;

    public String getStringSupply() {
        return String.valueOf(supply);
    }

    public String getStringBuy() {
        return String.valueOf(buy);
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
