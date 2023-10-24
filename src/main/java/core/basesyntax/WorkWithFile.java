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

    public void getStatistic(String fromFileName, String toFileName) throws RuntimeException {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        String statisticData;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            statisticData = readStatisticData(reader);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writeStatisticData(writer, statisticData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }

    }

    private String readStatisticData(BufferedReader reader) throws IOException {
        int supplyAmount = 0;
        int buyAmount = 0;
        String line;

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

        return createReport(supplyAmount, buyAmount);
    }

    private String createReport(int supplyAmount, int buyAmount) {
        StringBuilder builder = new StringBuilder();
        return builder
                    .append(OperationType.supply.name())
                    .append(SPECIFIED_CHARACTER)
                    .append(supplyAmount)
                    .append(System.lineSeparator())
                    .append(OperationType.buy)
                    .append(SPECIFIED_CHARACTER)
                    .append(buyAmount)
                    .append(System.lineSeparator())
                    .append("result")
                    .append(SPECIFIED_CHARACTER)
                    .append((supplyAmount - buyAmount))
                    .toString();
    }

    private void writeStatisticData(BufferedWriter writer, String data) throws IOException {
        writer.write(data);
    }
}
