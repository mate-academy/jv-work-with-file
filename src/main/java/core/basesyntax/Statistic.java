package core.basesyntax;

public class Statistic {
    private int supply = 0;
    private int buy = 0;

    public int getSupply() {
        return supply;
    }

    public void addSupply(int supply) {
        this.supply += supply;
    }

    public int getBuy() {
        return buy;
    }

    public void addBuy(int buy) {
        this.buy += buy;
    }

    public int getResult() {
        return supply - buy;
    }
}
