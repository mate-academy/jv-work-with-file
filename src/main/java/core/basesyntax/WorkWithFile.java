package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFile(fromFileName);
        String report = generateReport(lines);
        writeToFile(report, toFileName);
    }

    private String[] readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fileName, e);
        }
    }

    private String generateReport(String[] lines) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : lines) {
            String[] fields = line.split(SEPARATOR);
            String operation = fields[OPERATION_INDEX];
            int amount = Integer.parseInt(fields[AMOUNT_INDEX]);
            if (OPERATION_SUPPLY.equals(operation)) {
                totalSupply += amount;
            } else if (OPERATION_BUY.equals(operation)) {
                totalBuy += amount;
            }
        }

        int result = calculateResult(totalSupply, totalBuy);
        StringBuilder report = new StringBuilder();
        report.append(OPERATION_SUPPLY).append(SEPARATOR).append(totalSupply).append("\n");
        report.append(OPERATION_BUY).append(SEPARATOR).append(totalBuy).append("\n");
        report.append("result").append(SEPARATOR).append(result).append("\n");
        return report.toString();
    }

    private int calculateResult(int totalSupply, int totalBuy) {
        return totalSupply - totalBuy;
    }

    private void writeToFile(String report, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file " + fileName, e);
        }
    }
}
