package core.basesyntax;

import java.util.List;

public class ReportService {
    private static final String COMMA_SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT_VALUE = "result";
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    private static final int THIRD_ELEMENT = 2;

    public int[] calculateValues(List<String> inputList) {
        int lastBuyAmount = 0;
        int lastSupplyAmount = 0;
        for (String currentString : inputList) {
            String[] splitLines = currentString.split(COMMA_SEPARATOR);
            String currentOperation = splitLines[FIRST_ELEMENT];
            int currentAmount = Integer.parseInt(splitLines[SECOND_ELEMENT]);
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
                .append(calculatedValues[FIRST_ELEMENT])
                .append(System.lineSeparator())
                .append(BUY_OPERATION)
                .append(COMMA_SEPARATOR)
                .append(calculatedValues[SECOND_ELEMENT])
                .append(System.lineSeparator())
                .append(RESULT_VALUE)
                .append(COMMA_SEPARATOR)
                .append(calculatedValues[THIRD_ELEMENT]);

        return resultReportBuilder.toString();
    }

}
