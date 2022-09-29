package core.basesyntax;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    private final BuffReader buffReader = new BuffReader();
    private final CountSupplyAndBuy countSupplyAndBuy = new CountSupplyAndBuy();
    private final CreateReport createReport = new CreateReport();

    public void getStatistic(String fromFileName, String toFileName) {
        String fileRead = buffReader.readFromFile(fromFileName);
        String[] splittedWithSpace = fileRead.split(SPACE);
        int totalSupply = countSupplyAndBuy.countSupply(splittedWithSpace);
        int totalBuy = countSupplyAndBuy.countBuy(splittedWithSpace);
        String result = "result" + COMMA + (totalSupply - totalBuy);
        String[] arrayWithReport = createReport.reportForFile(totalSupply,totalBuy,result);
        buffReader.writeInFile(arrayWithReport, toFileName);
    }
}
