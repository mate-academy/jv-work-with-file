package core.basesyntax;

public class CreateReportMessage {

    private final CountBuysAndSupply count = new CountBuysAndSupply();
    private final int allSupply = count.countSupply();
    private final int allBuys = count.countBuys();

    private final String totalSupply = WorkWithFile.SUPPLY + WorkWithFile.COMMA
            + allSupply + System.lineSeparator();
    private final String totalBuys = WorkWithFile.BUY + WorkWithFile.COMMA
            + allBuys + System.lineSeparator();
    private final String reportMessage = WorkWithFile.REPORT + WorkWithFile.COMMA
            + (allSupply - allBuys);
    private final String[] reportArray = {totalSupply, totalBuys, reportMessage};

    public String[] getReportArray() {
        return reportArray;
    }
}
