package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String LABEL_SUPPLY = "supply";
    private static final String LABEL_BUY = "buy";
    private static final String LABEL_RESULT = "result";
    private static final int VALUE_INDEX = 1;
    private static final int LABEL_INDEX = 0;
    private static final int EXPECTED_PARTS_COUNT = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading data from the file ", e);
        }
        return content.toString();
    }

    private String generateReport(String fileContent) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = fileContent.split(NEW_LINE);
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            if (parts.length == EXPECTED_PARTS_COUNT) {
                String label = parts[LABEL_INDEX];
                int value = Integer.parseInt(parts[VALUE_INDEX]);
                if (LABEL_SUPPLY.equals(label)) {
                    supplyTotal += value;
                } else if (LABEL_BUY.equals(label)) {
                    buyTotal += value;
                }
            }
        }

        int result = supplyTotal - buyTotal;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(LABEL_SUPPLY).append(SEPARATOR).append(supplyTotal)
                .append(System.lineSeparator())
                .append(LABEL_BUY).append(SEPARATOR).append(buyTotal)
                .append(System.lineSeparator())
                .append(LABEL_RESULT).append(SEPARATOR).append(result);

        return reportBuilder.toString();
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing data to the file ", e);
        }
    }
}
