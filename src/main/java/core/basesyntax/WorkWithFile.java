package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static Map<String, List<Integer>> transactions = new HashMap<>();
    private static Map<String, String> results = new HashMap<>();

    public void getStatistic(String fromFileName, String toFileName) {
        if (results.containsKey(fromFileName)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write(results.get(fromFileName));
            } catch (IOException e) {
                throw new RuntimeException("Can't correctly write data to file " + toFileName, e);
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            transactions.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (!transactions.containsKey(type)) {
                    transactions.put(type, new ArrayList<>());
                }
                transactions.get(type).add(amount);
            }

            int totalBuy = 0;
            int totalSupply = 0;
            int countBuy = 0;
            int countSupply = 0;
            for (Map.Entry<String, List<Integer>> entry : transactions.entrySet()) {
                String type = entry.getKey();
                List<Integer> amounts = entry.getValue();
                int total = amounts.stream().mapToInt(Integer::intValue).sum();
                if (type.equals("supply")) {
                    totalSupply += total;
                    countSupply += amounts.size();
                } else if (type.equals("buy")) {
                    totalBuy += total;
                    countBuy += amounts.size();
                }
            }
            int result = totalSupply - totalBuy;
            String resultString = String.format("supply,%d\nbuy,%d\nresult,%d\n",
                    totalSupply, totalBuy, result);
            writer.write(resultString);
            results.put(fromFileName, resultString);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fromFileName, e);
        }
    }
}
