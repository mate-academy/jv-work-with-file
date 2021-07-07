package core.basesyntax;

public class OperationTable {
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_RESULT = "result";
    private static final String OPERATION_SEPARATOR = ",";

    private String[] operationTypes;
    private int[] amounts;
    private int actualLength = 0;
    private int buyAmount = 0;
    private int supplyAmount = 0;

    public OperationTable(int rows) {
        operationTypes = new String[rows];
        amounts = new int[rows];
        for (int i = 0; i < rows; i++) {
            amounts[i] = 0;
        }
    }

    public void addRow(String operationType, int amount) {
        actualLength++;
        operationTypes[actualLength - 1] = operationType;
        amounts[actualLength - 1] = amount;
        if (operationType.equals(OPERATION_BUY)) {
            buyAmount += amount;
        } else if (operationType.equals(OPERATION_SUPPLY)) {
            supplyAmount += amount;
        }
    }

    public void sumUpTable() {
        //creating sub table that will replace original OperationTable after summing
        String[] operationNamesArray = {OPERATION_SUPPLY, OPERATION_BUY};
        OperationTable resultOperationTable = new OperationTable(operationNamesArray.length);
        resultOperationTable.operationTypes = operationNamesArray;
        resultOperationTable.actualLength = operationNamesArray.length;
        for (int resTabIndex = 0; resTabIndex < resultOperationTable.actualLength; resTabIndex++) {
            for (int baseTableIndex = 0; baseTableIndex < operationTypes.length; baseTableIndex++) {
                if (operationTypes[baseTableIndex]
                        .equals(resultOperationTable.operationTypes[resTabIndex])) {
                    resultOperationTable.amounts[resTabIndex] += amounts[baseTableIndex];
                }
            }
        }
        resultOperationTable.buyAmount = buyAmount;
        resultOperationTable.supplyAmount = supplyAmount;
        replaceOperationTable(resultOperationTable);
    }

    private void replaceOperationTable(OperationTable operationTable) {
        operationTypes = operationTable.operationTypes;
        amounts = operationTable.amounts;
        actualLength = operationTable.actualLength;
        buyAmount = operationTable.buyAmount;
        supplyAmount = operationTable.supplyAmount;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < actualLength; i++) {
            stringBuilder.append(operationTypes[i])
                    .append(OPERATION_SEPARATOR)
                    .append(amounts[i])
                    .append(System.lineSeparator());
        }
        stringBuilder.append(OPERATION_RESULT)
                .append(OPERATION_SEPARATOR)
                .append(supplyAmount - buyAmount);
        return stringBuilder.toString().trim();
    }
}
