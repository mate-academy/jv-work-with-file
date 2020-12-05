package core.basesyntax;

public class Report {
    private int sumBuy;
    private int sumSupply;
    private int result;

    public void setSumBuy(int sumBuy) {
        this.sumBuy = sumBuy;
    }

    public void setSumSupply(int sumSupply) {
        this.sumSupply = sumSupply;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getSumBuy() {
        return sumBuy;
    }

    public int getSumSupply() {
        return sumSupply;
    }

    public int getResult() {
        return result;
    }
}
