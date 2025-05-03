package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<Integer> supplyAmounts = new ArrayList<>();
        List<Integer> buyAmounts = new ArrayList<>();

        readAndParseData(fromFileName, supplyAmounts, buyAmounts);

        int[] statistics = calculateStatistics(supplyAmounts, buyAmounts);

        writeResults(toFileName, statistics);
    }

    public void readAndParseData(String fromFileName, List<Integer> supplyAmounts,
                                  List<Integer> buyAmounts) {
        try (BufferedReader fromFile = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = fromFile.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length == 2) {
                    String operationType = values[0].trim();
                    int amount = Integer.parseInt(values[1].trim());

                    if (operationType.equalsIgnoreCase(SUPPLY)) {
                        supplyAmounts.add(amount);
                    } else if (operationType.equalsIgnoreCase(BUY)) {
                        buyAmounts.add(amount);

                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    public static int sumList(List<Integer> amounts) {
        int sum = 0;
        for (int amount : amounts) {
            sum += amount;
        }
        return sum;
    }

    public int[] calculateStatistics(List<Integer> supplyAmounts, List<Integer> buyAmounts) {
        int totalSupply = sumList(supplyAmounts);
        int totalBuy = sumList(buyAmounts);
        int result = totalSupply - totalBuy;

        return new int[]{totalSupply, totalBuy, result};
    }

    public void writeResults(String toFileName, int[] statistics) {
        try (BufferedWriter toFile = new BufferedWriter(new FileWriter(toFileName))) {
            toFile.write(SUPPLY + "," + statistics[0]);
            toFile.newLine();
            toFile.write(BUY + "," + statistics[1]);
            toFile.newLine();
            toFile.write(RESULT + "," + statistics[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
