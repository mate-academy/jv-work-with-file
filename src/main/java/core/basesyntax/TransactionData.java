package core.basesyntax;

public class TransactionData {
    protected int supply;
    protected int buy;
    protected int result;

    public TransactionData(int supply, int buy, int result) {
        this.supply = supply;
        this.buy = buy;
        this.result = result;
    }
}
