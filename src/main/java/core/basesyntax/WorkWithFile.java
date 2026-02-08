package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String const1 = "supply";
    private static final String const2 = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readStatistics(fromFileName);
        writeReport(toFileName, statistics);
    }

    private static int[] readStatistics(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                int amount = Integer.parseInt(parts[1]);
                if (parts[0].equals(const1)) {
                    supplySum += amount;
                } else if (parts[0].equals(const2)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        return new int[]{supplySum,buySum};
    }

    private static void writeReport(String toFileName, int[] statistics) {
        int supplySum = statistics[0];
        int buySum = statistics[1];
        int result = supplySum - buySum;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supplySum);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buySum);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }
}
