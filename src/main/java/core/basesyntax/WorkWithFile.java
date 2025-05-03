package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int STRING_PARTS_LENGHT = 2;

    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        supply = 0;
        buy = 0;
        readFromFile(fromFileName);
        String report = generateReport();
        writeToFile(toFileName, report);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                if (parts.length == STRING_PARTS_LENGHT) {
                    String operationName = parts[OPERATION_TYPE_INDEX].trim();
                    int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

                    if (operationName.equals(SUPPLY)) {
                        supply += amount;
                    } else if (operationName.equals(BUY)) {
                        buy += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
