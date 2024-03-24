package core.basesyntax.models;

import core.basesyntax.enums.OperationType;

public class OperationDataResult {
    private int supply;
    private int buy;

    public OperationDataResult(Operation[] operationData) {
        this.supply = getOperationSum(operationData, OperationType.SUPPLY.name().toLowerCase());
        this.buy = getOperationSum(operationData, OperationType.BUY.name().toLowerCase());
    }

    @Override
    public String toString() {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + getResult();
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
