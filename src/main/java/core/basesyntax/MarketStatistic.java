package core.basesyntax;

public class MarketStatistic {

    private int buyCount;
    private int supplyCount;

    public MarketStatistic(int buyCount, int supplyCount) {
        this.buyCount = buyCount;
        this.supplyCount = supplyCount;
    }

    public int getSupplyCount() {
        return supplyCount;
    }

    public void setSupplyCount(int supplyCount) {
        this.supplyCount = supplyCount;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
}
