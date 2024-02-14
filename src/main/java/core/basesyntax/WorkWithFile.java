package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private int supplyTotal;
    private int buyTotal;

    public int getSupplyTotal() {
        return supplyTotal;
    }

    public int getBuyTotal() {
        return buyTotal;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        try {

            try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processFile(line, supplyTotal, buyTotal);
                }
            }
            int result = supplyTotal - buyTotal;

            writeReport(toFileName, supplyTotal, buyTotal, result);
        } catch (IOException e) {
            throw new RuntimeException("Error processing file", e);
        }
        supplyTotal = 0;
        buyTotal = 0;
    }

    private void processFile(String line, int supplyTotal, int buyTotal) {
        String[] lines = line.split(",");
        if (lines.length == 2) {
            String operationType = lines[0].trim();
            int amount = Integer.parseInt(lines[1].trim());

            if ("supply".equals(operationType)) {
                supplyTotal += amount;
            }

            if ("buy".equals(operationType)) {
                buyTotal += amount;
            }
            this.supplyTotal = supplyTotal;
            this.buyTotal = buyTotal;
        }
    }

    private static void writeReport(String toFileName, int supplyTotal, int buyTotal, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error wtiting to file " + toFileName, e);
        }
    }
}
