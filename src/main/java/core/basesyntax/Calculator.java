package core.basesyntax;

public class Calculator {

    public void calculateReportItems(Report report, ReportItem reportItem) {
        if (Name.BUY.equals(reportItem.getName())) {
            sumReportItems(report, reportItem, Name.BUY);
        } else {
            sumReportItems(report, reportItem, Name.SUPPLY);
        }
    }

    private void sumReportItems(Report report, ReportItem reportItem, Name name) {
        if (Name.BUY.equals(name)) {
            report.addBuy(reportItem.getValue());
        } else {
            report.addSupply(reportItem.getValue());
        }
    }
}
