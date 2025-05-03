package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = calculateStatistics(fromFileName);
        String report = generateResult(totals[0], totals[1]);
        writeStatistics(toFileName, report);
    }

    private int[] calculateStatistics(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = readFromFile(fromFileName)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                if (parts.length < 2) {
                    continue;
                }
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals(SUPPLY)) {
                    totalSupply += amount;
                } else if (operation.equals(BUY)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read data from file " + fromFileName, e);
        }

        return new int[]{totalSupply, totalBuy};
    }

    private BufferedReader readFromFile(String fromFileName) throws IOException {
        return new BufferedReader(new FileReader(fromFileName));
    }

    private String generateResult(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;
        StringBuilder output = new StringBuilder();
        output.append(SUPPLY).append(COMMA).append(totalSupply).append(System.lineSeparator());
        output.append(BUY).append(COMMA).append(totalBuy).append(System.lineSeparator());
        output.append(RESULT).append(COMMA).append(result).append(System.lineSeparator());
        return output.toString();
    }

    private void writeStatistics(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write data to file " + toFileName, e);
        }
    }
}
