package core.basesyntax;

public class WorkWithFile {
    private final BufferReadWrite buffer = new BufferReadWrite();
    private final BuyAndSupplyCounter buyAndSupplyCounter = new BuyAndSupplyCounter();
    private final ReportMessageCreate reportMessageCreate = new ReportMessageCreate();
    private final String space = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = buffer.readFromFile(fromFileName);
        String[] textWithoutSpaces = textFromFile.split(space);
        int supplyAmount = buyAndSupplyCounter.countSupply(textWithoutSpaces);
        int buyAmount = buyAndSupplyCounter.countBuys(textWithoutSpaces);
        String[] reportMessage = reportMessageCreate.createReport(supplyAmount,buyAmount);
        buffer.writeInFile(toFileName, reportMessage);
    }
}
