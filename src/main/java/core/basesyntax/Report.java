package core.basesyntax;

public class Report {
    private int supplySum;
    private int buySum;
    private int result;

    public enum ReportFieldName {
        SUPPLY,
        BUY,
        RESULT
    }

    public Report() {
        this.supplySum = 0;
        this.buySum = 0;
        this.result = 0;
    }

    public int getSupplySum() {
        return supplySum;
    }

    public int getBuySum() {
        return buySum;
    }

    public int getResult() {
        result = supplySum - buySum;
        return result;
    }

    public void setSupplySum(int supplySum) {
        this.supplySum = supplySum;
    }

    public void setBuySum(int buySum) {
        this.buySum = buySum;
    }

    public StringBuilder createReport() {
        StringBuilder report = new StringBuilder();
        return report.append(ReportFieldName.SUPPLY.name().toLowerCase())
                .append(",")
                .append(getSupplySum()).append(System.lineSeparator())
                .append(ReportFieldName.BUY.name().toLowerCase())
                .append(",")
                .append(getBuySum()).append(System.lineSeparator())
                .append(ReportFieldName.RESULT.name().toLowerCase())
                .append(",")
                .append(getResult()).append(System.lineSeparator());
    }
}
