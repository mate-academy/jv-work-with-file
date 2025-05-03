package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final String READ_ERROR_MESSAGE = "Can't read data from the file: ";
    private static final String WRITE_ERROR_MESSAGE = "Can't write data to the file: ";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String createReport(String[] data) {
        StringBuilder result = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String entry : data) {
            String[] parts = entry.split(DELIMITER);
            String operationType = parts[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);
            if (operationType.equals(SUPPLY)) {
                supplyAmount += amount;
            }
            if (operationType.equals(BUY)) {
                buyAmount += amount;
            }

        }
        result.append(SUPPLY).append(DELIMITER).append(supplyAmount).append(System.lineSeparator());
        result.append(BUY).append(DELIMITER).append(buyAmount).append(System.lineSeparator());
        result.append(RESULT).append(DELIMITER).append(supplyAmount - buyAmount);
        return result.toString();
    }

    private String[] readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(READ_ERROR_MESSAGE + fileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (Exception e) {
            throw new RuntimeException(WRITE_ERROR_MESSAGE + fileName, e);
        }
    }
}
