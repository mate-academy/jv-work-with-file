package core.basesyntax;

public class Statistic {
    private final int supplySum;
    private final int buySum;

    public Statistic(int supplySum, int buySum) {
        this.supplySum = supplySum;
        this.buySum = buySum;
    }

    public int getSupplySum() {
        return supplySum;
    }

    public int getBuySum() {
        return buySum;
    }

    public int getResult() {
        return supplySum - buySum;
    }
}
