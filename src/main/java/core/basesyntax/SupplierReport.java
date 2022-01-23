package core.basesyntax;

public class SupplierReport {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private final StringBuilder toWrite = new StringBuilder();

    public String creatReport(String data) {
        String[] readiedData = (data).split(" ");
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String s : readiedData) {
            if (s.contains(SUPPLY)) {
                supplyAmount += Integer.parseInt(s.split(",")[1]);
            } else {
                buyAmount += Integer.parseInt(s.split(",")[1]);
            }
        }
        toWrite.append(SUPPLY).append(",").append(supplyAmount).append(System.lineSeparator());
        toWrite.append(BUY).append(",").append(buyAmount).append(System.lineSeparator());
        toWrite.append(RESULT).append(",").append(supplyAmount - buyAmount);
        return toWrite.toString();
    }
}
