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
    private static final String DELIMITER = ",";
    private static final String NL = System.lineSeparator();

    public String getStatistic(String fromFileName, String toFileName) {
        int[] totals = readAndCalculate(fromFileName);
        String report = buildReport(totals[0], totals[1]);
        writeToFile(toFileName, report);
        return report;
    }

    private int[] readAndCalculate(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals(SUPPLY)) {
                    totalSupply += amount;
                } else if (operation.equals(BUY)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        }

        return new int[]{totalSupply, totalBuy};
    }

    private String buildReport(int totalSupply, int totalBuy) {
        StringBuilder builder = new StringBuilder();

        builder.append(SUPPLY).append(DELIMITER).append(totalSupply).append(NL);
        builder.append(BUY).append(DELIMITER).append(totalBuy).append(NL);
        builder.append(RESULT).append(DELIMITER).append(totalSupply - totalBuy);

        return builder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + toFileName, e);
        }
    }
}
