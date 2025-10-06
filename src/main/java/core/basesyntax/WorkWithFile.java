package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DELIMITER = ",";
    private static final String LS = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readFromFile(fromFileName);
        String report = createReport(totals[0], totals[1]);
        writeToFile(toFileName, report);
    }

    private int[] readFromFile(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(DELIMITER);
                if (parts.length != 2) {
                    continue;
                }
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (SUPPLY.equals(operation)) {
                    supplyTotal += amount;
                } else if (BUY.equals(operation)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return new int[]{supplyTotal, buyTotal};
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        return new StringBuilder()
                .append(SUPPLY).append(DELIMITER).append(supply).append(LS)
                .append(BUY).append(DELIMITER).append(buy).append(LS)
                .append("result").append(DELIMITER).append(result)
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}
