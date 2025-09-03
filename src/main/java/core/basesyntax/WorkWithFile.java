package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    // Constants
    private static final String OP_SUPPLY = "supply";
    private static final String OP_BUY = "buy";
    private static final String LABEL_RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readAndAggregate(fromFileName);
        int totalSupply = totals[0];
        int totalBuy = totals[1];
        String report = buildReport(totalSupply, totalBuy);
        writeReport(toFileName, report);
    }

    private int[] readAndAggregate(String fromFileName) {
        int[] totals = new int[2]; // [totalSupply, totalBuy]
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    int amount = Integer.parseInt(parts[1].trim());
                    if (parts[0].equals(OP_SUPPLY)) {
                        totals[0] += amount;
                    } else if (parts[0].equals(OP_BUY)) {
                        totals[1] += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return totals;
    }

    private String buildReport(int totalSupply, int totalBuy) {
        StringBuilder report = new StringBuilder();
        int result = totalSupply - totalBuy;
        report.append(OP_SUPPLY).append(DELIMITER).append(totalSupply).append(System.lineSeparator());
        report.append(OP_BUY).append(DELIMITER).append(totalBuy).append(System.lineSeparator());
        report.append(LABEL_RESULT).append(DELIMITER).append(result).append(System.lineSeparator());
        return report.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}