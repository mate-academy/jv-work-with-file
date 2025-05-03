package core.basesyntax;

public class SupplierReport {
    private static final String COMA_SEPARATOR = ",";
    private static final String SPACE_SEPARATOR = " ";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy,";
    private static final String RESULT = "RESULT,";
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int INDEX_OF_SUPPLY = 0;
    private static final int INDEX_OF_BUY = 1;
    private static final int INDEX_OF_RESULT = 2;

    public int[] countData(String data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        int result = 0;
        String[] readiedData = (data).split(SPACE_SEPARATOR);
        for (String s : readiedData) {
            if (s.contains(SUPPLY)) {
                supplyAmount += Integer.parseInt(s.split(COMA_SEPARATOR)[INDEX_OF_AMOUNT]);
            } else {
                buyAmount += Integer.parseInt(s.split(COMA_SEPARATOR)[INDEX_OF_AMOUNT]);
            }
        }
        result = supplyAmount - buyAmount;
        return new int[] {supplyAmount, buyAmount, result};
    }

    public String creatReport(int[] stock) {
        StringBuilder toWrite = new StringBuilder();
        toWrite.append(SUPPLY).append(COMA_SEPARATOR);
        toWrite.append(stock[INDEX_OF_SUPPLY]).append(System.lineSeparator());
        toWrite.append(BUY).append(stock[INDEX_OF_BUY]).append(System.lineSeparator());
        toWrite.append(RESULT).append(stock[INDEX_OF_RESULT]);
        return toWrite.toString();
    }
}
