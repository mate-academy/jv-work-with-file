package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        int[] totals = processLines(lines);
        int supplyTotal = totals[SUPPLY_INDEX];
        int buyTotal = totals[BUY_INDEX];

        writeResults(toFileName, supplyTotal, buyTotal);
    }

    private int[] processLines(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
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

        return new int[]{supplyTotal, buyTotal};
    }

    private List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void writeResults(String toFileName, int supplyTotal, int buyTotal) {
        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(String.format("supply,%d\n", supplyTotal));
            writer.write(String.format("buy,%d\n", buyTotal));
            writer.write(String.format("result,%d\n", result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
