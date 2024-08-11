package core.basesyntax.model;

public class Report {
    private static final int DEFAULT_AMOUNT = 0;
    private int supply;
    private int buy;

    public Report() {
        this(DEFAULT_AMOUNT, DEFAULT_AMOUNT);
    }

    public Report(int totalSupply, int totalBuy) {
        this.supply = totalSupply;
        this.buy = totalBuy;
    }

    public void incrementSupply(int increment) {
        this.supply += increment;
    }

    public void incrementBuy(int increment) {
        this.buy += increment;
    }

    public int getSupply() {
        return supply;
    }

    public int getBuy() {
        return buy;
    }

    public int getResult() {
        return supply - buy;
    }
}
