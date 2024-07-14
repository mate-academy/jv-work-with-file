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

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            supply = processFile(fromFileName, OPERATION_SUPPLY);
            buy = processFile(fromFileName, OPERATION_BUY);
        } catch (IOException e) {
            throw new RuntimeException("Error processing file: " + e.getMessage(), e);
        }

        int result = supply - buy;

        try {
            writeReportToFile(toFileName, supply, buy, result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report to file: " + e.getMessage(), e);
        }
    }

    private int processFile(String fileName, String operationType) throws IOException {
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
        }
        return total;
    }

    private void writeReportToFile(String fileName, int supply, int buy, int result) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        }
    }
}
