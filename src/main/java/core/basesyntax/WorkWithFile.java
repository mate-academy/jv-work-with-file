package core.basesyntax;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final int DEFAULT_NUMBER = 0;
    private final BufferReadWrite buffer = new BufferReadWrite();
    private final BuyAndSupplyCounter buyAndSupplyCounter = new BuyAndSupplyCounter();
    private final ReportMessageCreate reportMessageCreate = new ReportMessageCreate();

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount;
        int buyAmount;
        String textFromFile = buffer.readFromFile(fromFileName);
        String[] textWithoutSpaces = textFromFile.split(SPACE);
        supplyAmount = buyAndSupplyCounter.countSupply(textWithoutSpaces);
        buyAmount = buyAndSupplyCounter.countBuys(textWithoutSpaces);
        String[] reportMessage = reportMessageCreate.createReport(supplyAmount,buyAmount);
        buffer.writeInFile(toFileName, reportMessage);
    }
}
