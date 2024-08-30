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
    private static final String SEPARATOR = ",";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts.length == 2) {
                    String operation = parts[INDEX_OF_OPERATION];
                    int amount = Integer.parseInt(parts[INDEX_OF_AMOUNT]);

                    if (operation.equals(SUPPLY)) {
                        totalSupply += amount;
                    } else if (operation.equals(BUY)) {
                        totalBuy += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file " + fromFileName, e);
        }
        int result = totalSupply - totalBuy;
        writeStatisticsToFile(toFileName, totalSupply, totalBuy, result);
    }

    private void writeStatisticsToFile(String fileName, int supplyTotal, int buyTotal, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(generateReport(supplyTotal, buyTotal, result));
        } catch (IOException e) {
            throw new RuntimeException("Can't save data in the file " + fileName, e);
        }
    }

    private String generateReport(int supplyTotal, int buyTotal, int result) {
        return new StringBuilder()
                .append(SUPPLY).append(SEPARATOR).append(supplyTotal).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buyTotal).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result).append(System.lineSeparator())
                .toString();
    }
}


