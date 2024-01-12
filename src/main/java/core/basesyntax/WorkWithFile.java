package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = "\\s*,\\s*";
    private static final String REPORT_FORMAT = "supply,%d%sbuy,%d%sresult,%d%s";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            return content.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + fromFileName, e);
        }
    }

    private String generateReport(String data) {
        int totalSupply = 0;
        int totalBuy = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            String operationType = parts[OPERATION_TYPE_INDEX];
            int quantity = Integer.parseInt(parts[QUANTITY_INDEX]);

            if ("supply".equals(operationType)) {
                totalSupply += quantity;
            } else if ("buy".equals(operationType)) {
                totalBuy += quantity;
            }
        }

        return String.format(REPORT_FORMAT, totalSupply, System.lineSeparator(),
                totalBuy, System.lineSeparator(), (totalSupply - totalBuy), System.lineSeparator());
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }
}
