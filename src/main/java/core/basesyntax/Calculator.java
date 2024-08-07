package core.basesyntax;

public class Calculator {
    public void calculateReportItems(Report report, ReportItem reportItem) {
        if (OperationType.BUY == reportItem.getOperationType()) {
            sumReportItems(report, reportItem, OperationType.BUY);
        } else {
            sumReportItems(report, reportItem, OperationType.SUPPLY);
        }
    }

    private void sumReportItems(Report report, ReportItem reportItem, OperationType operationType) {
        if (OperationType.BUY == operationType) {
            report.addBuysAmount(reportItem.getValue());
        } else {
            report.addSuppliesAmount(reportItem.getValue());
        }
    }
}
