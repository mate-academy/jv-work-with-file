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
    private static final String SEPARATOR = ",";

    public static void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        int[] totals = readAndAggregate(fromFileName);
        supplySum = totals[0];
        buySum = totals[1];

        String report = buildReport(supplySum, buySum);

        writeReport(toFileName, report);
    }

    private static int[] readAndAggregate(String fileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts.length != 2) {
                    continue;
                }

                String operation = parts[0].trim();
                int amount;

                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "Invalid number in file " + fileName + " on line: \"" + line + "\"", e);
                }

                if (SUPPLY.equals(operation)) {
                    supplySum += amount;
                } else if (BUY.equals(operation)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file " + fileName, e);
        }

        return new int[]{supplySum, buySum};
    }

    private static String buildReport(int supplySum, int buySum) {
        int result = supplySum - buySum;

        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(SEPARATOR).append(supplySum).append(System.lineSeparator());
        sb.append(BUY).append(SEPARATOR).append(buySum).append(System.lineSeparator());
        sb.append(RESULT).append(SEPARATOR).append(result).append(System.lineSeparator());

        return sb.toString();
    }

    private static void writeReport(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }

}
