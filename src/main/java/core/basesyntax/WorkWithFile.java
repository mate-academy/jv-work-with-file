package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = calculateStatistics(fromFileName);
        writeResultToFile(statistics, toFileName);
    }

    private int[] calculateStatistics(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(COMMA);
                String operation = data[0];
                int amount = Integer.parseInt(data[1]);

                if (operation.equals(SUPPLY)) {
                    totalSupply += amount;
                } else if (operation.equals(BUY)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
        return new int[]{totalSupply, totalBuy};
    }

    private void writeResultToFile(int[] statistics, String toFileName) {
        int totalSupply = statistics[0];
        int totalBuy = statistics[1];
        int result = totalSupply - totalBuy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + COMMA + totalSupply + System.lineSeparator());
            bufferedWriter.write(BUY + COMMA + totalBuy + System.lineSeparator());
            bufferedWriter.write(RESULT + COMMA + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
