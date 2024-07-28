package core.basesyntax;

public class Calculator {

    public void calculateStatistics(Report report, ReportItem reportItem) {
        if (Name.BUY.equals(reportItem.getName())) {
            report.addBuy(reportItem.getValue());
        } else {
            report.addSupply(reportItem.getValue());
        }
    }
}
