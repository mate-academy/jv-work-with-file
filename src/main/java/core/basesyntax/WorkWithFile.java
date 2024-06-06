package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] output = readFile(fromFileName);
        String result = getResult(output);
        writeToFile(result, toFileName);
    }

    private int[] readFile(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA_DELIMITER);
                String operation = parts[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

                if (SUPPLY.equals(operation)) {
                    supplyTotal += amount;
                } else if (BUY.equals(operation)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing file: " + fromFileName, e);
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private String getResult(int[] output) {
        int supplyTotal = output[0];
        int buyTotal = output[1];
        int result = supplyTotal - buyTotal;
        return String.format("%s,%d%n%s,%d%n%s,%d", SUPPLY, supplyTotal,
                BUY, buyTotal,
                RESULT, result);
    }

    private void writeToFile(String result, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }

    private void writeFile(String toFileName, int supplyTotal,
                           int buyTotal, int result) {
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(COMMA_DELIMITER)
                .append(supplyTotal).append(System.lineSeparator());
        sb.append(BUY).append(COMMA_DELIMITER)
                .append(buyTotal).append(System.lineSeparator());
        sb.append(RESULT).append(COMMA_DELIMITER)
                .append(result).append(System.lineSeparator());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }
}
