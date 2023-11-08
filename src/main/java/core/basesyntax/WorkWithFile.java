package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT = "result,";
    private static final int PARTS_LENGTH = 2;
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String readFileContent(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return content.toString();
    }

    private String generateReport(String fileContent) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            if (parts.length == PARTS_LENGTH) {
                String operationType = parts[OPERATION_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

                if (OPERATION_SUPPLY.equals(operationType)) {
                    supplyTotal += amount;
                } else if (OPERATION_BUY.equals(operationType)) {
                    buyTotal += amount;
                }
            }
        }

        int result = supplyTotal - buyTotal;

        return OPERATION_SUPPLY + SEPARATOR
                + supplyTotal
                + System.lineSeparator()
                + OPERATION_BUY
                + SEPARATOR
                + buyTotal
                + System.lineSeparator()
                + RESULT
                + result;
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
