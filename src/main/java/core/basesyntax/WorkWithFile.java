package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_CONS = "supply";
    private static final String BUY_CONS = "buy";
    private static final String RESULT_CONS = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readFile(fromFileName);
        int totalSupply = totals[0];
        int totalBuy = totals[1];
        writeToFile(toFileName, totalSupply, totalBuy);
    }

    public int[] readFile(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                String type = values[0];
                int amount = Integer.parseInt(values[1]);
                if (type.equals(SUPPLY_CONS)) {
                    totalSupply += amount;
                } else if (type.equals(BUY_CONS)) {
                    totalBuy += amount;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from this file", e);
        }
        return new int[]{totalSupply, totalBuy};
    }

    public void writeToFile(String toFileName, int totalSupply, int totalBuy) {
        StringBuilder stringBuilder = new StringBuilder();
        int result = totalSupply - totalBuy;
        stringBuilder.append(SUPPLY_CONS).append(",")
                .append(totalSupply).append(System.lineSeparator());
        stringBuilder.append(BUY_CONS).append(",")
                .append(totalBuy).append(System.lineSeparator());
        stringBuilder.append(RESULT_CONS).append(",").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file", e);
        }
    }
}

