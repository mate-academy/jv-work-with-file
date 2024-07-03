package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";

    static void getStatistic(String fromFileName, String toFileName) {
        int[] data = readData(fromFileName);
        int[] results = calculateResults(data[0], data[1]);
        writeResults(toFileName, results[0], results[1], results[2]);
    }

    private static int[] readData(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String transactionType = parts[0];
                int transactionAmount = Integer.parseInt(parts[1]);

                totalSupply += "supply".equals(transactionType) ? transactionAmount : 0;
                totalBuy += "buy".equals(transactionType) ? transactionAmount : 0;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
        return new int[]{totalSupply, totalBuy};
    }

    private static int[] calculateResults(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;
        return new int[]{totalSupply, totalBuy, result};
    }

    private static void writeResults(String toFileName, int totalSupply, int totalBuy, int result) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(totalSupply).append(System.lineSeparator());
        builder.append("buy,").append(totalBuy).append(System.lineSeparator());
        builder.append("result,").append(result).append(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
