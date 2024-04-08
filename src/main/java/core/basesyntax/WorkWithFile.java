package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String OUTPUT_FORMAT = "%s,%d\n%s,%d\nresult,%d";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals;
        try {
            totals = readAndProcessInput(fromFileName);
            writeOutput(toFileName, totals);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }

    private int[] readAndProcessInput(String fileName) throws IOException {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
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
        }
        return new int[]{supplyTotal, buyTotal};
    }

    private void writeOutput(String fileName, int[] totals) {
        int result = totals[0] - totals[1];

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(String.format(OUTPUT_FORMAT,
                    SUPPLY, totals[0],
                    BUY, totals[1], result));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + fileName, e);
        }
    }
}
