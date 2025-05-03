package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int REQUIRED_PARTS_LENGTH = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readLinesFromFile(fromFileName);
        int totalSupply = calculateTotalSupply(lines);
        int totalBuy = calculateTotalBuy(lines);
        int result = calculateResult(totalSupply, totalBuy);
        String report = generateReport(totalSupply, totalBuy, result);

        writeToFile(report, toFileName);
    }

    private String[] readLinesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file: "
                     + e.getMessage(), e);
        }
    }

    private int calculateTotalSupply(String[] lines) {
        int totalSupply = 0;
        for (String line : lines) {
            totalSupply += extractAmount(line, SUPPLY);
        }
        return totalSupply;
    }

    private int calculateTotalBuy(String[] lines) {
        int totalBuy = 0;
        for (String line : lines) {
            totalBuy += extractAmount(line, BUY);
        }
        return totalBuy;
    }

    private int extractAmount(String line, String operation) {
        String[] parts = line.trim().split(",");
        if (hasValidParts(parts) && operation.equals(parts[OPERATION_INDEX])) {
            return Integer.parseInt(parts[AMOUNT_INDEX]);
        }
        return 0;
    }

    private int calculateResult(int totalSupply, int totalBuy) {
        return totalSupply - totalBuy;
    }

    private String generateReport(int totalSupply, int totalBuy, int result) {
        return "supply," + totalSupply + System.lineSeparator()
                + "buy," + totalBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file: "
                    + toFileName, e);
        }
    }

    private boolean hasValidParts(String[] parts) {
        return parts.length == REQUIRED_PARTS_LENGTH;
    }
}
