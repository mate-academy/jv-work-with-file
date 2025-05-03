package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String VALUE_SEPARATOR = ",";
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final int AMOUNT_POSITION = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readDataFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeDataToFile(report, toFileName);
    }

    private String readDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String lineContent = reader.readLine();
            while (lineContent != null) {
                stringBuilder.append(System.lineSeparator())
                        .append(lineContent);
                lineContent = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from the file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        String[] linesOfData = dataFromFile.split(System.lineSeparator());

        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line: linesOfData) {
            if (line.isEmpty()) {
                continue;
            }
            String[] dataSet = line.split(VALUE_SEPARATOR);
            if (dataSet[OPERATION_TYPE_POSITION].equals(OPERATION_SUPPLY)) {
                supplyAmount += Integer.parseInt(dataSet[AMOUNT_POSITION]);
            } else {
                buyAmount += Integer.parseInt(dataSet[AMOUNT_POSITION]);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPERATION_SUPPLY)
                .append(VALUE_SEPARATOR)
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(OPERATION_BUY)
                .append(VALUE_SEPARATOR)
                .append(buyAmount)
                .append(System.lineSeparator())
                .append(OPERATION_RESULT)
                .append(VALUE_SEPARATOR)
                .append(supplyAmount - buyAmount);
        return stringBuilder.toString();
    }

    private void writeDataToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + fileName, e);
        }
    }
}
