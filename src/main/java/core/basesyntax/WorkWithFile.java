package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return lines;
    }

    private String createReport(List<String> data) {
        int supplySum = 0;
        int buySum = 0;

        for (String record : data) {
            String[] parts = record.split(SEPARATOR);
            String operation = parts[OPERATION_INDEX];
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

            if (SUPPLY.equals(operation)) {
                supplySum += amount;
            } else if (BUY.equals(operation)) {
                buySum += amount;
            }
        }

        int result = supplySum - buySum;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(supplySum).append(System.lineSeparator());
        report.append(BUY).append(SEPARATOR).append(buySum).append(System.lineSeparator());
        report.append(RESULT).append(SEPARATOR).append(result).append(System.lineSeparator());

        return report.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
