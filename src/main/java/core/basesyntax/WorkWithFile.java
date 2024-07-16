package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readData(fromFileName);
        String report = generateReport(totals);
        writeData(toFileName, report);
    }

    private int[] readData(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals(SUPPLY)) {
                    supplyTotal += amount;
                } else if (operation.equals(BUY)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return new int[]{supplyTotal, buyTotal};
    }

    private void writeData(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private String generateReport(int[] totals) {
        int supplyTotal = totals[0];
        int buyTotal = totals[1];
        int result = supplyTotal - buyTotal;

        StringBuilder report = new StringBuilder();
        report.append(buildLine(SUPPLY, supplyTotal));
        report.append(buildLine(BUY, buyTotal));
        report.append(buildLine("result", result));

        return report.toString();
    }

    private String buildLine(String operation, int amount) {
        return operation + DELIMITER + amount + System.lineSeparator();
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("input.csv", "output.csv");
    }
}
