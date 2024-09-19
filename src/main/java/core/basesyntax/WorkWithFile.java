package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final String RESULT_OF_REPORT = "result";
    private static final String COMMA = ",";
    private static final int ARRAY_INDEX_FOR_AMOUNT = 1;
    private static final int ARRAY_INDEX_FOR_TOTAL_SUPPLY_AMOUNT = 0;
    private static final int ARRAY_INDEX_FOR_TOTAL_BUY_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuyAmounts = readFromFile(fromFileName);
        String statistic = generateReport(supplyAndBuyAmounts);
        writeToFile(toFileName, statistic);
    }

    private int[] readFromFile(String fromFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String stringNumber = value.split(COMMA)[ARRAY_INDEX_FOR_AMOUNT];
                if (value.startsWith(SUPPLY_OPERATION_TYPE)) {
                    supplyAmount += Integer.parseInt(stringNumber);
                }
                if (value.startsWith(BUY_OPERATION_TYPE)) {
                    buyAmount += Integer.parseInt(stringNumber);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return new int[]{supplyAmount, buyAmount};
    }

    private String generateReport(int[] supplyAndBuyAmounts) {
        int finalAmount = supplyAndBuyAmounts[ARRAY_INDEX_FOR_TOTAL_SUPPLY_AMOUNT]
                - supplyAndBuyAmounts[ARRAY_INDEX_FOR_TOTAL_BUY_AMOUNT];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_OPERATION_TYPE).append(COMMA)
                        .append(supplyAndBuyAmounts[ARRAY_INDEX_FOR_TOTAL_SUPPLY_AMOUNT])
                                .append(System.lineSeparator())
                                        .append(BUY_OPERATION_TYPE).append(COMMA)
                        .append(supplyAndBuyAmounts[ARRAY_INDEX_FOR_TOTAL_BUY_AMOUNT])
                                .append(System.lineSeparator())
                                        .append(RESULT_OF_REPORT).append(COMMA)
                        .append((finalAmount));

        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String statistic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
