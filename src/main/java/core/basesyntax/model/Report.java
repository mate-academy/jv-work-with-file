package core.basesyntax.model;

import java.util.List;

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

    public void ofOperations(List<Operation> operations) {
        for (Operation operation : operations) {
            if (operation.getOperationType().equals(OperationType.SUPPLY)) {
                incrementSupply(operation.getAmount());
            } else {
                incrementBuy(operation.getAmount());
            }
        }
    }

    private void incrementSupply(int increment) {
        this.supply += increment;
    }

    private void incrementBuy(int increment) {
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
