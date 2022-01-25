package core.basesyntax;

public class WorkWithFile extends FileManagement {
    private static final String SPLITTER_FOR_FILE_NAME = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int AMOUNT_SUPPLY = 0;
    private static final int AMOUNT_BUY = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String[] split = workWithFile.readFile(fromFileName).split(System.lineSeparator());
        int buyValue = AMOUNT_BUY;
        int supplyValue = AMOUNT_SUPPLY;
        for (String s : split) {
            String[] operationAndAmount = s.split(SPLITTER_FOR_FILE_NAME);
            if (operationAndAmount[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            } else {
                buyValue += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            }
        }
        int result = supplyValue - buyValue;
        StringBuilder stringBuilderReport = new StringBuilder(SUPPLY)
                .append(",").append(supplyValue).append(System.lineSeparator())
                        .append(BUY).append(",").append(buyValue).append(System.lineSeparator())
                        .append(RESULT).append(",").append(result);

        workWithFile.writeFile(stringBuilderReport.toString(), toFileName);
    }
}
