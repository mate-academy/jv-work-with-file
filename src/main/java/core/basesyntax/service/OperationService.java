package core.basesyntax.service;

public class OperationService {
    private static final String COMMA = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public int[] getResult(String[] infoFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : infoFromFile) {
            String[] parts = line.split(COMMA);
            String operationType = parts[OPERATION_TYPE_INDEX].trim();
            int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

            switch (operationType) {
                case OPERATION_SUPPLY:
                    totalSupply += amount;
                    break;
                case OPERATION_BUY:
                    totalBuy += amount;
                    break;
                default: throw new IllegalArgumentException(
                        "Invalid operation type: " + operationType);
            }
        }
        int result = totalSupply - totalBuy;
        return new int[]{totalSupply, totalBuy, result};
    }
}
