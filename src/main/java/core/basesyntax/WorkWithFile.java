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
        int supplyTotal = 0;
        int buyTotal = 0;
        int result = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
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
        result = supplyTotal - buyTotal;
        String report = new StringBuilder()
                .append(SUPPLY).append(DELIMITER).append(supplyTotal).append(LS)
                .append(BUY).append(DELIMITER).append(buyTotal).append(LS)
                .append("result").append(DELIMITER).append(result)
                .toString();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}
