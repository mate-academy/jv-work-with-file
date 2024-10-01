package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuy = readData(fromFileName);
        int result = processStatistics(supplyAndBuy[0], supplyAndBuy[1]);
        writeData(toFileName, supplyAndBuy[0], supplyAndBuy[1], result);
    }

    private int[] readData(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String operation = data[0];
                int amount = Integer.parseInt(data[1]);

                if (operation.equals("supply")) {
                    totalSupply += amount;
                } else if (operation.equals("buy")) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + fromFileName, e);
        }

        return new int[]{totalSupply, totalBuy};
    }

    private int processStatistics(int totalSupply, int totalBuy) {
        return totalSupply - totalBuy;
    }

    private void writeData(String toFileName, int totalSupply, int totalBuy, int result) {
        StringBuilder sb = new StringBuilder();
        sb.append("supply,").append(totalSupply).append(System.lineSeparator());
        sb.append("buy,").append(totalBuy).append(System.lineSeparator());
        sb.append("result,").append(result).append(System.lineSeparator());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }
}
