package core.basesyntax;

import java.util.List;

public class ReportGenerator {
    public static final String COMMA_SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT_VALUE = "result";

    public int[] calculateValues(List<String> inputList) {
        int lastBuyAmount = 0;
        int lastSupplyAmount = 0;
        for (String currentString : inputList) {
            String[] splitLines = currentString.split(COMMA_SEPARATOR);
            String currentOperation = splitLines[0];
            int currentAmount = Integer.parseInt(splitLines[1]);
            if (currentOperation.equals(BUY_OPERATION)) {
                lastBuyAmount = lastBuyAmount + currentAmount;
            } else if (currentOperation.equals(SUPPLY_OPERATION)) {
                lastSupplyAmount = lastSupplyAmount + currentAmount;
            }
        }
        int result = lastSupplyAmount - lastBuyAmount;
        int[] calculatedValues = new int[]{lastSupplyAmount, lastBuyAmount, result};
        return calculatedValues;
    }

    public String generateReport(int[] calculatedValues) {
        StringBuilder resultReportBuilder = new StringBuilder();
        resultReportBuilder.append(SUPPLY_OPERATION)
                .append(COMMA_SEPARATOR)
                .append(calculatedValues[0])
                .append(System.lineSeparator())
                .append(BUY_OPERATION)
                .append(COMMA_SEPARATOR)
                .append(calculatedValues[1])
                .append(System.lineSeparator())
                .append(RESULT_VALUE)
                .append(COMMA_SEPARATOR)
                .append(calculatedValues[2]);

        return resultReportBuilder.toString();
    }

}
