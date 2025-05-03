package core.basesyntax;

public class Statistic {
    private int buy;
    private int supply;

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public void addBuy(int buy) {
        this.buy += buy;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public void addSupply(int supply) {
        this.supply += supply;
    }

    public int getResult() {
        return supply - buy;
    }
}
