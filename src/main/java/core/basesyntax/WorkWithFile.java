package core.basesyntax;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMA = ",";


    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> data = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMA);
                if (parts.length == 2) {
                    String operationType = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());

                    data.put(operationType, data.getOrDefault(operationType, 0) + amount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int supplyTotal = data.get(SUPPLY);
        int buyTotal = data.get(BUY);
        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY + COMA + supplyTotal);
            writer.newLine();
            writer.write(BUY + COMA + buyTotal);
            writer.newLine();
            writer.write(RESULT + COMA + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

