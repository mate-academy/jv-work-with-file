package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = calculateStatistics(fromFileName);
        String report = buildReport(statistics[0], statistics[1]);
        writeReport(report, toFileName);
    }

    private int[] calculateStatistics(String fileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(SEPARATOR);

                if (parts.length < 2) {
                    continue;
                }

                int amount = Integer.parseInt(parts[1].trim());

                if (SUPPLY.equals(parts[0].trim())) {
                    totalSupply += amount;
                } else if (BUY.equals(parts[0].trim())) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }

        return new int[]{totalSupply, totalBuy};
    }

    private String buildReport(int supply, int buy) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator());
        report.append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator());
        report.append(RESULT).append(SEPARATOR).append(supply - buy).append(System.lineSeparator());

        return report.toString();
    }

    private void writeReport(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
