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
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String data = readFile(fromFileName);
            int[] totals = processInput(data);
            writeOutput(toFileName, totals);
        } catch (IOException e) {
            throw new RuntimeException("Can't process file: " + fromFileName, e);
        }
    }

    private String readFile(String fileName) throws IOException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        }
        return data.toString();
    }

    private int[] processInput(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts.length != 2) {
                continue;
            }

            String operation = parts[INDEX_OPERATION].trim();
            int amount = Integer.parseInt(parts[INDEX_AMOUNT].trim());

            if (SUPPLY.equals(operation)) {
                supplyTotal += amount;
            } else if (BUY.equals(operation)) {
                buyTotal += amount;
            }
        }
        return new int[]{supplyTotal, buyTotal};
    }

    private void writeOutput(String fileName, int[] totals) throws IOException {
        int result = totals[INDEX_OPERATION] - totals[INDEX_AMOUNT];
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(String.format(OUTPUT_FORMAT, SUPPLY,
                    totals[INDEX_OPERATION], BUY,
                    totals[INDEX_AMOUNT], result));
        }
    }
}
