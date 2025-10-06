package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DELIMITER = ",";
    private static final String LS = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readAndAggregate(fromFileName);
        String report = buildReport(totals[0], totals[1]);
        writeReport(toFileName, report);
    }

    private int[] readAndAggregate(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader br = Files.newBufferedReader(
                Paths.get(fromFileName), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) {
                    continue;
                }
                String[] parts = trimmedLine.split(DELIMITER);
                if (parts.length != 2) {
                    continue;
                }
                String operation = parts[0].trim();
                String amountRaw = parts[1].trim();
                final int amount;
                try {
                    amount = Integer.parseInt(amountRaw);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid amount "
                            + amountRaw + " at line "
                            + lineNumber + "in file "
                            + fromFileName, e);
                }
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

    private String buildReport(int supplyTotal, int buyTotal) {
        int result = supplyTotal - buyTotal;
        return new StringBuilder()
                .append(SUPPLY).append(DELIMITER).append(supplyTotal).append(LS)
                .append(BUY).append(DELIMITER).append(buyTotal).append(LS)
                .append("result").append(DELIMITER).append(result)
                .toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get(toFileName), StandardCharsets.UTF_8)) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}
