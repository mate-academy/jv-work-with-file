package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String DELIMITER = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        supply = processFile(fromFileName, OPERATION_SUPPLY);
        buy = processFile(fromFileName, OPERATION_BUY);

        String report = generateReport(supply, buy);

        writeReportToFile(toFileName, report);
    }

    private int processFile(String fileName, String operationType) {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String type = parts[0];
                if (type.equals(operationType)) {
                    int amount = Integer.parseInt(parts[1]);
                    total += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing file: " + fileName, e);
        }
        return total;
    }

    private String generateReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(OPERATION_SUPPLY).append(DELIMITER)
                .append(supply).append(System.lineSeparator());
        reportBuilder.append(OPERATION_BUY).append(DELIMITER)
                .append(buy).append(System.lineSeparator());
        reportBuilder.append(OPERATION_RESULT).append(DELIMITER)
                .append(result).append(System.lineSeparator());
        return reportBuilder.toString();
    }

    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report to file: " + fileName, e);
        }
    }
}
