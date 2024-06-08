package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        int[] totals = calculateTotals(dataFromFile);
        int totalSupply = totals[0];
        int totalBuy = totals[1];

        String report = generateReportString(totalSupply, totalBuy);
        writeToFile(toFileName, report);
    }

    private int[] calculateTotals(String[] dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : dataFromFile) {
            if (!line.isEmpty()) {
                String[] parts = line.split(COMMA);
                String operationType = parts[OPERATION_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

                if (SUPPLY.equals(operationType)) {
                    totalSupply += amount;
                } else if (BUY.equals(operationType)) {
                    totalBuy += amount;
                }
            }
        }

        return new int[]{totalSupply, totalBuy};
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + fromFileName, e);
        }
        return fileContent.toString().split(NEW_LINE);
    }

    private String generateReportString(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;
        return SUPPLY + COMMA + totalSupply + NEW_LINE
                + BUY + COMMA + totalBuy + NEW_LINE
                + RESULT + COMMA + result + NEW_LINE;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing the file: " + toFileName, e);
        }
    }
}
