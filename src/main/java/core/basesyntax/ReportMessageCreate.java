package core.basesyntax;

public class ReportMessageCreate {
    public String[] createReport(int allSupply, int allBuys) {
        String report = "result";
        String supply = "supply";
        String buy = "buy";
        String comma = ",";
        String totalSupply = supply + comma + allSupply + System.lineSeparator();
        String totalBuys = buy + comma + allBuys + System.lineSeparator();
        String reportMessage = report + comma + (allSupply - allBuys);
        return new String[] {totalSupply, totalBuys, reportMessage};
    }
}
