package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<Integer> supplyAmounts = new ArrayList<>();
        List<Integer> buyAmounts = new ArrayList<>();

        try (BufferedReader fromFile = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = fromFile.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length == 2) {
                    String operationType = values[0].trim();
                    int amount = Integer.parseInt(values[1].trim());

                    if (operationType.equalsIgnoreCase("supply")) {
                        supplyAmounts.add(amount);
                    } else if (operationType.equalsIgnoreCase("buy")) {
                        buyAmounts.add(amount);

                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        int totalSupply = sumList(supplyAmounts);
        int totalBuy = sumList(buyAmounts);
        int result = totalSupply - totalBuy;

        try (BufferedWriter toFile = new BufferedWriter(new FileWriter(toFileName))) {
            toFile.write("supply," + totalSupply);
            toFile.newLine();
            toFile.write("buy," + totalBuy);
            toFile.newLine();
            toFile.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }

    private static int sumList(List<Integer> amounts) {
        int sum = 0;
        for (int amount : amounts) {
            sum += amount;
        }
        return sum;
    }
}
