package core.basesyntax;

public class MarketData {
    private int totalSupplyAmount;
    private int totalBuyAmount;

    public void addSupplyAmount(int supplyAmount) {
        totalSupplyAmount += supplyAmount;
    }

    public void addBuyAmount(int buyAmount) {
        totalBuyAmount += buyAmount;
    }

    public int getTotalSupplyAmount() {
        return totalSupplyAmount;
    }

    public int getTotalBuyAmount() {
        return totalBuyAmount;
    }

    public int calculateResult() {
        return totalSupplyAmount - totalBuyAmount;
    }
}
