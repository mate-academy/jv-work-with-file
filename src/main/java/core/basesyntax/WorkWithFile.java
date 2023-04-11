package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFile(fromFileName);
        String report = generateReport(content);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + fileName, e);
        }
    }

    private String generateReport(String content) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = content.split(System.lineSeparator());
        for (String line : lines) {
            String[] values = line.split(DELIMITER);
            if (values.length == 2 && values[INDEX_OPERATION].equals(SUPPLY)) {
                try {
                    int value = Integer.parseInt(values[INDEX_VALUE]);
                    supplyTotal += value;
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid value found in file: "
                            + values[INDEX_VALUE], e);
                }
            } else if (values.length == 2 && values[INDEX_OPERATION].equals(BUY)) {
                try {
                    int value = Integer.parseInt(values[INDEX_VALUE]);
                    buyTotal += value;
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid value found in file: "
                            + values[INDEX_VALUE], e);
                }
            }
        }

        int result = supplyTotal - buyTotal;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(DELIMITER).append(supplyTotal).append(NEW_LINE);
        report.append(BUY).append(DELIMITER).append(buyTotal).append(NEW_LINE);
        report.append(RESULT).append(DELIMITER).append(result).append(NEW_LINE);

        return report.toString();
    }

    private void writeToFile(String report, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report to file: " + fileName, e);
        }
    }
}
