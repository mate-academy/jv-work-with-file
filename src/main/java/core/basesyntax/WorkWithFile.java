package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readDataAndCalculateTotals(fromFileName);
        int supplyTotal = totals[0];
        int buyTotal = totals[1];

        String report = createReport(supplyTotal, buyTotal);
        writeToFile(toFileName, report);
    }

    private int[] readDataAndCalculateTotals(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                if (parts.length != 2) {
                    continue;
                }

                String operation = parts[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

                if (SUPPLY.equals(operation)) {
                    supplyTotal += amount;
                } else if (BUY.equals(operation)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format in the file: " + fromFileName, e);
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        String lineSeparator = System.lineSeparator();

        return SUPPLY + COMMA + supply + lineSeparator + BUY + COMMA + buy
                + lineSeparator + RESULT + COMMA + result;
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + fileName, e);
        }
    }
}
