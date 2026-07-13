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
    private static final int PLACE_IN_ARRAY = 0;
    private static final int ARRAY_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeFile(toFileName, report);
    }

    private String createReport(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[PLACE_IN_ARRAY];
                int amount = Integer.parseInt(parts[ARRAY_NUMBER]);
                if (operation.equals(SUPPLY)) {
                    supplyTotal += amount;
                } else if (operation.equals(BUY)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(",").append(supplyTotal).append(System.lineSeparator());
        stringBuilder.append(BUY).append(",").append(buyTotal).append(System.lineSeparator());
        stringBuilder.append(RESULT).append(",").append(supplyTotal - buyTotal);

        return stringBuilder.toString();
    }

    private void writeFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}

