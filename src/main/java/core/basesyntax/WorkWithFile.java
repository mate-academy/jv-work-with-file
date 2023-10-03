package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = processLines(fromFileName);
        int supplyTotal = totals[0];
        int buyTotal = totals[1];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writeResults(writer, supplyTotal, buyTotal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] processLines(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operationType = parts[0];
                    int amount = Integer.parseInt(parts[1]);

                    if (operationType.equals("supply")) {
                        supplyTotal += amount;
                    } else if (operationType.equals("buy")) {
                        buyTotal += amount;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private void writeResults(BufferedWriter writer, int supplyTotal, int buyTotal)
            throws IOException {
        int result = supplyTotal - buyTotal;

        writer.write("supply," + supplyTotal);
        writer.newLine();
        writer.write("buy," + buyTotal);
        writer.newLine();
        writer.write("result," + result);
    }
}

