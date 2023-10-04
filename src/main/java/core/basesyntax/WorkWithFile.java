package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA_SEPARATOR);
                if (parts.length == 2) {
                    String operationType = parts[OPERATION_TYPE_INDEX];
                    int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

                    if ("supply".equals(operationType)) {
                        supplyTotal += amount;
                    } else if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + fromFileName, e);
        }

        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writeLine(writer, "supply", supplyTotal);
            writeLine(writer, "buy", buyTotal);
            writeLine(writer, "result", result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the file: " + toFileName, e);
        }
    }

    private void writeLine(BufferedWriter writer, String key, int value) throws IOException {
        writer.write(key + COMMA_SEPARATOR + value + System.lineSeparator());
    }
}
