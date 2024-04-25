package core.basesyntax;

import static core.basesyntax.OperationType.BUY;
import static core.basesyntax.OperationType.SUPPLY;

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

    public String stringSummary(char delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY)
                .append(delimiter)
                .append(getSupply())
                .append("\n")
                .append(BUY)
                .append(delimiter)
                .append(getBuy())
                .append("\n")
                .append("result")
                .append(delimiter)
                .append(getResult());
        return stringBuilder.toString();
    }
}
