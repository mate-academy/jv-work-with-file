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

    public static Report ofOperations(List<Operation> operations) {
        Report report = new Report();
        for (Operation operation : operations) {
            if (operation.getOperationType().equals(OperationType.SUPPLY)) {
                report.incrementSupply(operation.getAmount());
            } else {
                report.incrementBuy(operation.getAmount());
            }
        }
        return report;
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
