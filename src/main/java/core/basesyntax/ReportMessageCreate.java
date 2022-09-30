package core.basesyntax;

public class ReportMessageCreate {

    public String[] createReport(int allSupply, int allBuys) {
        String report = "result";
        String totalSupply = WorkWithFile.SUPPLY + WorkWithFile.COMMA
                + allSupply + System.lineSeparator();
        String totalBuys = WorkWithFile.BUY + WorkWithFile.COMMA
                + allBuys + System.lineSeparator();
        String reportMessage = report + WorkWithFile.COMMA
                + (allSupply - allBuys);
        return new String[] {totalSupply, totalBuys, reportMessage};
    }
}
