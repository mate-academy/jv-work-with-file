package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int PARTS_AMOUNT = 2;
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName)
            throws IllegalArgumentException {
        int[] totals = readFile(fromFileName);
        int supplyTotal = totals[0];
        int buyTotal = totals[1];
        int result = supplyTotal - buyTotal;
        if (supplyTotal < 0 || buyTotal < 0 || result < 0) {
            throw new IllegalArgumentException("The file contains wrong data");
        }
        createReport(toFileName, supplyTotal, buyTotal, result);
    }

    private int[] readFile(String fromFileName) throws IllegalArgumentException {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == PARTS_AMOUNT) {
                    String type = parts[OPERATION_TYPE].trim();
                    int value = Integer.parseInt(parts[AMOUNT].trim());
                    if ("supply".equals(type)) {
                        supplyTotal += value;
                    } else if ("buy".equals(type)) {
                        buyTotal += value;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }
        return new int[]{supplyTotal, buyTotal};
    }

    private void createReport(String toFileName, int supplyTotal, int buyTotal, int result)
            throws IllegalArgumentException {
        StringBuilder content = new StringBuilder();
        content.append("supply,").append(supplyTotal).append(System.lineSeparator());
        content.append("buy,").append(buyTotal).append(System.lineSeparator());
        content.append("result,").append(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(content.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file");
        }
    }
}
