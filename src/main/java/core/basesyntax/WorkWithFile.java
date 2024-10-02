package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NAME_OF_OPERATION_SUPPLY = "supply";
    private static final String NAME_OF_OPERATION_BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SYMBOL = ",";
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int INDEX_OF_SUPPLY_AMOUNT = 0;
    private static final int INDEX_OF_BUY_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] operationsAmounts = readAmountsOfOperations(fromFileName);
        int supplyAmount = operationsAmounts[INDEX_OF_SUPPLY_AMOUNT];
        int buyAmount = operationsAmounts[INDEX_OF_BUY_AMOUNT];

        String report = createReportMessage(supplyAmount, buyAmount);
        writeReportToFile(report, toFileName);
    }

    private int[] readAmountsOfOperations(String fromFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            String[] lineFromInputfile;
            while (value != null) {
                lineFromInputfile = value.split(COMMA_SYMBOL);
                String operationType = lineFromInputfile[INDEX_OF_OPERATION_TYPE];
                int amount = Integer.parseInt(lineFromInputfile[INDEX_OF_AMOUNT]);
                if (operationType.equals(NAME_OF_OPERATION_SUPPLY)) {
                    supplyAmount += amount;
                } else if (operationType.equals(NAME_OF_OPERATION_BUY)) {
                    buyAmount += amount;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supplyAmount, buyAmount};
    }

    private String createReportMessage(int supplyAmount, int buyAmount) {
        int resultAmount = supplyAmount - buyAmount;
        StringBuilder stringBuilderResult = new StringBuilder();
        stringBuilderResult.append(NAME_OF_OPERATION_SUPPLY).append(COMMA_SYMBOL)
                .append(supplyAmount).append(System.lineSeparator())
                .append(NAME_OF_OPERATION_BUY).append(COMMA_SYMBOL)
                .append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(COMMA_SYMBOL)
                .append(resultAmount).append(System.lineSeparator());
        return stringBuilderResult.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
