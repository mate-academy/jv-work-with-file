package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_1 = "supply";
    private static final String OPERATION_2 = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_FOR_CURRENT_OPERATION = 0;
    private static final int INDEX_FOR_OPERATION_1_TOTAL_AMOUNT = 0;
    private static final int INDEX_FOR_OPERATION_2_TOTAL_AMOUNT = 1;
    private static final String OPERATION_AND_AMOUNT_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        int[] totalAmounts = readFromFileAndGetAmounts(fromFileName);
        String report = createReport(totalAmounts);
        writeToFile(toFile, toFileName, report);
    }

    private int[] readFromFileAndGetAmounts(String fromFileName) {
        int supplyTotalAmount = 0;
        int buyTotalAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] currentOpearationAndAmount
                        = currentLine.split(OPERATION_AND_AMOUNT_SEPARATOR);
                int amount = Integer.parseInt(currentOpearationAndAmount[1]);
                if (currentOpearationAndAmount[INDEX_FOR_CURRENT_OPERATION].equals(OPERATION_1)) {
                    supplyTotalAmount += amount;
                } else {
                    buyTotalAmount += amount;
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
        return new int[] {supplyTotalAmount, buyTotalAmount};
    }

    private String createReport(int[] totalAmounts) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPERATION_1 + OPERATION_AND_AMOUNT_SEPARATOR)
                .append(totalAmounts[INDEX_FOR_OPERATION_1_TOTAL_AMOUNT])
                .append(System.lineSeparator());
        stringBuilder.append(OPERATION_2 + OPERATION_AND_AMOUNT_SEPARATOR)
                .append(totalAmounts[INDEX_FOR_OPERATION_2_TOTAL_AMOUNT])
                .append(System.lineSeparator());
        stringBuilder.append(RESULT + OPERATION_AND_AMOUNT_SEPARATOR)
                .append(totalAmounts[INDEX_FOR_OPERATION_1_TOTAL_AMOUNT]
                        - totalAmounts[INDEX_FOR_OPERATION_2_TOTAL_AMOUNT]);
        return stringBuilder.toString();
    }

    private void writeToFile(File toFile, String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }
}
