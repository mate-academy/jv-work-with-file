package core.basesyntax;

public class WorkWithFile {
    private static final String SPLITTER_FOR_FILE_NAME = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int AMOUNT_SUPPLY = 0;
    private static final int AMOUNT_BUY = 0;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        FileManager fileManager = new FileManager();
        WorkWithFile workWithFile = new WorkWithFile();
        String[] split = fileManager.readFile(fromFileName).split(System.lineSeparator());
        int[] buildReport = workWithFile.separateFile(split);
        StringBuilder stringBuilderReport = new StringBuilder(SUPPLY).append(SPLITTER_FOR_FILE_NAME)
                .append(buildReport[SUPPLY_INDEX]).append(System.lineSeparator()).append(BUY)
                .append(SPLITTER_FOR_FILE_NAME).append(buildReport[BUY_INDEX])
                .append(System.lineSeparator()).append(RESULT)
                .append(SPLITTER_FOR_FILE_NAME).append(buildReport[RESULT_INDEX]);

        fileManager.writeFile(stringBuilderReport.toString(), toFileName);
    }

    public int[] separateFile(String[] split) {
        int buyValue = AMOUNT_BUY;
        int supplyValue = AMOUNT_SUPPLY;
        for (String line : split) {
            String[] operationAndAmount = line.split(SPLITTER_FOR_FILE_NAME);
            if (operationAndAmount[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            } else {
                buyValue += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            }
        }
        int result = supplyValue - buyValue;
        return new int[] {supplyValue, buyValue, result};
    }
}
