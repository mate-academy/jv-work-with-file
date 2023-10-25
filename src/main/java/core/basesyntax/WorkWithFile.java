package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SPECIFIED_CHARACTER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String OPERATION_RESULT_TITLE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File sourceFile = new File(fromFileName);
        File destinationFile = new File(toFileName);

        String statisticData = readStatisticData(sourceFile);
        writeStatisticData(destinationFile, statisticData);
    }

    private String readStatisticData(File file) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] dataRow = line.split(SPECIFIED_CHARACTER);
                    if (dataRow[OPERATION_TYPE_INDEX].equals(OperationType.supply.name())) {
                        supplyAmount += Integer.parseInt(dataRow[AMOUNT_INDEX]);
                    }
                    if (dataRow[OPERATION_TYPE_INDEX].equals(OperationType.buy.name())) {
                        buyAmount += Integer.parseInt(dataRow[AMOUNT_INDEX]);
                    }
                }
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + file, e);
        }
        return createReport(supplyAmount, buyAmount);
    }

    private String createReport(int supplyAmount, int buyAmount) {
        StringBuilder builder = new StringBuilder();
        return builder
                    .append(OperationType.supply.name())
                    .append(SPECIFIED_CHARACTER)
                    .append(supplyAmount)
                    .append(LINE_SEPARATOR)
                    .append(OperationType.buy)
                    .append(SPECIFIED_CHARACTER)
                    .append(buyAmount)
                    .append(LINE_SEPARATOR)
                    .append(OPERATION_RESULT_TITLE)
                    .append(SPECIFIED_CHARACTER)
                    .append((supplyAmount - buyAmount))
                    .toString();
    }

    private void writeStatisticData(File file, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file, e);
        }
    }
}
