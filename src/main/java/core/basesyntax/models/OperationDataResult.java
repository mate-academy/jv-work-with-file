package core.basesyntax.models;

import core.basesyntax.enums.OperationType;

public class OperationDataResult {
    private static final String SUPPLY_OPERATION_NAME = OperationType.SUPPLY.name().toLowerCase();
    private static final String BUY_OPERATION_NAME = OperationType.BUY.name().toLowerCase();
    private static final String RESULT_OPERATION_NAME = "result";
    private StringBuilder stringBuilder = new StringBuilder();
    private int supply;
    private int buy;
    private String separator;

    public OperationDataResult(Operation[] operationData, String separator) {
        this.supply = getOperationSum(operationData, SUPPLY_OPERATION_NAME);
        this.buy = getOperationSum(operationData, BUY_OPERATION_NAME);
        this.separator = separator;
    }

    @Override
    public String toString() {
        return stringBuilder
                .append(SUPPLY_OPERATION_NAME).append(separator).append(supply)
                .append(System.lineSeparator())
                .append(BUY_OPERATION_NAME).append(separator).append(buy)
                .append(System.lineSeparator())
                .append(RESULT_OPERATION_NAME).append(separator).append(getResult())
                .toString();
    }

    private int getOperationSum(Operation[] operationData, String operationType) {
        int sumCount = 0;
        for (Operation operationDataItem : operationData) {
            if (operationDataItem.getOperationType().equals(operationType)) {
                sumCount += operationDataItem.getAmount();
            }
        }
        return sumCount;
    }

    private int getResult() {
        return supply - buy;
    }
}
