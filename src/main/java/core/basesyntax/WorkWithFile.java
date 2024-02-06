package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String DELIMITER = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeReport(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("File " + fromFileName + " cannot be found");
        }
        return data.toString();
    }

    private String createReport(String data) {
        int supplySum = 0;
        int buySum = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            String operationType = parts[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);
            if (operationType.equals(OPERATION_SUPPLY)) {
                supplySum += amount;
            } else if (operationType.equals(OPERATION_BUY)) {
                buySum += amount;
            }
        }
        stringBuilder.append(OPERATION_SUPPLY).append(DELIMITER).append(supplySum)
                .append(System.lineSeparator())
                .append(OPERATION_BUY).append(DELIMITER).append(buySum)
                .append(System.lineSeparator())
                .append("result").append(DELIMITER).append(supplySum - buySum);
        return stringBuilder.toString();
    }

    private void writeReport(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write on file " + toFileName);
        }
    }
}
