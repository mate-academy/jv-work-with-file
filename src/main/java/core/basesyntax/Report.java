package core.basesyntax;

public class Report {
    private int sumBuy;
    private int sumSupply;

    public void setSumBuy(int sumBuy) {
        this.sumBuy = sumBuy;
    }

    public void setSumSupply(int sumSupply) {
        this.sumSupply = sumSupply;
    }

    public int getSumBuy() {
        return sumBuy;
    }

    public int getSumSupply() {
        return sumSupply;
    }

    public int getResult() {
        return sumSupply - sumBuy;
    }
}
