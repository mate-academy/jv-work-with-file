package core.basesyntax;

public class Report {
    private int buyAmount;
    private int supplyAmount;

    public int getBuysAmount() {
        return buyAmount;
    }

    public int getSuppliesAmount() {
        return supplyAmount;
    }

    public void addBuysAmount(int value) {
        buyAmount += value;
    }

    public void addSuppliesAmount(int value) {
        supplyAmount += value;
    }

    public int getResult() {
        return supplyAmount - buyAmount;
    }
}
