package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String ADDED = "buy";
    private static final String LOSE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> sellAndBuy = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineWords = bufferedReader.readLine();
            while (lineWords != null) {
                String[] line = lineWords.split(",");
                String key = line[0];
                Integer value = Integer.parseInt(line[1]);
                if (sellAndBuy.containsKey(key)) {
                    sellAndBuy.put(key, sellAndBuy.get(key) + value);
                } else {
                    sellAndBuy.put(key, value);
                }
                lineWords = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String key = "result";
        int value = Math.abs(sellAndBuy.get(ADDED) - sellAndBuy.get(LOSE));
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Integer> kvp : sellAndBuy.entrySet()) {
                if (!kvp.getKey().equals(ADDED)) {
                    builder.append(kvp.getKey());
                    builder.append(",");
                    builder.append(kvp.getValue());
                    builder.append("\r\n");
                }
            }
            for (Map.Entry<String, Integer> kvp : sellAndBuy.entrySet()) {
                if (!kvp.getKey().equals(LOSE)) {
                    builder.append(kvp.getKey());
                    builder.append(",");
                    builder.append(kvp.getValue());
                    builder.append("\r\n");
                }
            }
            builder.append("result");
            builder.append(",");
            builder.append(value);
            builder.append("\r\n");
            String content = builder.toString().trim();
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
