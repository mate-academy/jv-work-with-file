package core.basesyntax;

class Report {
    private int supply;
    private int buy;

    public Report(int supply, int buy) {
        this.supply = supply;
        this.buy = buy;
    }

    public Report() {}


    public int getSupply() {
        return supply;
    }

    public int getBuy() {
        return buy;
    }

    public void addToSupply(int supplyToAdd) {
        supply += supplyToAdd;
    }

    public void addToBuy(int buyToAdd) {
        buy += buyToAdd;
    }
}
