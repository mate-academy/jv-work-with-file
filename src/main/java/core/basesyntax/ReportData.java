package core.basesyntax;

public class ReportData {

    private int supply = 0;
    private int buy = 0;

    public void addBuy(int buyAmount) {
        buy += buyAmount;
    }

    public void addSupply(int supplyAmount) {
        supply += supplyAmount;
    }

    public int getResult() {
        return supply - buy;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }
}
