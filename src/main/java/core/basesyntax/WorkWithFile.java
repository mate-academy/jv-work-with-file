package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String value = br.readLine();
            while (value != null) {
                String[] str = value.split(",");
                if (!data.containsKey(str[0])) {
                    data.put(str[0], Integer.parseInt(str[1]));
                } else {
                    data.put(str[0], data.get(str[0]) + Integer.parseInt(str[1]));
                }
                value = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        data.put("result", data.get("supply") - data.get("buy"));
        writeStatistic(toFileName, createStatistic(data));
    }

    private String createStatistic(HashMap<String, Integer> data) {
        String[] keys = data.keySet().toArray(new String[0]);
        StringBuilder builder = new StringBuilder();
        for (int i = keys.length - 1; i >= 0; i--) {
            builder.append(keys[i])
                    .append(',')
                    .append(data.get(keys[i]))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    private void writeStatistic(String toFileName, String toWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(toWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
