package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public int[] getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFile(fromFileName);
        Totals totals = calculateTotals(lines);
        writeFile(toFileName, totals);
        return new int[]{totals.getSupplyTotal(), totals.getBuyTotal(), totals.getResult()};
    }

    private String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Помилка обробки файлів", e);
        }
    }

    private Totals calculateTotals(String[] lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[0].trim();
            int amount = Integer.parseInt(parts[1].trim());

            if (SUPPLY.equals(operation)) {
                supplyTotal += amount;
            } else if (BUY.equals(operation)) {
                buyTotal += amount;
            }
        }

        int result = supplyTotal - buyTotal;
        return new Totals(supplyTotal, buyTotal, result);
    }

    private void writeFile(String toFileName, Totals totals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(SUPPLY + "," + totals.getSupplyTotal());
            writer.newLine();
            writer.write(BUY + "," + totals.getBuyTotal());
            writer.newLine();
            writer.write(RESULT + "," + totals.getResult());
        } catch (IOException e) {
            throw new RuntimeException("Помилка запису в файл", e);
        }
    }
}

class Totals {
    private final int supplyTotal;
    private final int buyTotal;
    private final int result;

    public Totals(int supplyTotal, int buyTotal, int result) {
        this.supplyTotal = supplyTotal;
        this.buyTotal = buyTotal;
        this.result = result;
    }

    public int getSupplyTotal() {
        return supplyTotal;
    }

    public int getBuyTotal() {
        return buyTotal;
    }

    public int getResult() {
        return result;
    }
}
