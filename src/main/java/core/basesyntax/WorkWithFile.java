package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            Map<String, Integer> operations = new LinkedHashMap<>();
            operations.put("supply", 0);
            operations.put("buy", 0);
            operations.put("result", 0);
            String value = reader.readLine();
            while (value != null) {
                String[] lineValue = value.split(",");
                operations.put(lineValue[0],
                        operations.get(lineValue[0]) + Integer.parseInt(lineValue[1]));
                value = reader.readLine();
            }
            operations.put("result", operations.get("supply") - operations.get("buy"));
            Set<String> keys = operations.keySet();
            for (String key : keys) {
                writer.write(key + "," + operations.get(key));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + e);
        }
    }
}
