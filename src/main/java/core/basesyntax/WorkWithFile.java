package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA_DIVIDER = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] sums = readFile(fromFileName);
        String report = generateReport(sums);
        writeToFile(toFileName, report);
    }

    private int[] readFile(String fileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(COMMA_DIVIDER);
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operation.equals(BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fileName, e);
        }
        return new int[]{supplySum, buySum};
    }

    private String generateReport(int[] sums) {
        int supplySum = sums[0];
        int buySum = sums[1];
        int result = supplySum - buySum;
        StringBuilder report = new StringBuilder();

        report.append(SUPPLY).append(COMMA_DIVIDER).append(supplySum).append(System.lineSeparator())
        .append(BUY).append(COMMA_DIVIDER).append(buySum).append(System.lineSeparator())
        .append(RESULT).append(COMMA_DIVIDER).append(result).append(System.lineSeparator());

        return report.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + fileName, e);
        }
    }
}
