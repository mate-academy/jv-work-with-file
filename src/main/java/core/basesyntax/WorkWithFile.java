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
    private static final String DECREASED = "supply";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> map = readingFromFile(fromFileName);
        String result = formingResult(map);
        String resultFromFile = writingFromFile(toFileName, result);
    }

    public HashMap<String, Integer> readingFromFile(String fromFileName) {
        HashMap<String, Integer> sellAndBuy = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineWords = bufferedReader.readLine();
            while (lineWords != null) {
                String[] line = lineWords.split(CSV_SEPARATOR);
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
            throw new RuntimeException("Can't read file", e);
        }
        return sellAndBuy;
    }

    public String formingResult(HashMap<String, Integer> sellAndBuy) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> kvp : sellAndBuy.entrySet()) {
            if (!kvp.getKey().equals(ADDED)) {
                builder.append(kvp.getKey());
                builder.append(CSV_SEPARATOR);
                builder.append(kvp.getValue());
                builder.append(System.lineSeparator());
            }
        }
        for (Map.Entry<String, Integer> kvp : sellAndBuy.entrySet()) {
            if (!kvp.getKey().equals(DECREASED)) {
                builder.append(kvp.getKey());
                builder.append(CSV_SEPARATOR);
                builder.append(kvp.getValue());
                builder.append(System.lineSeparator());
            }
        }
        int value = Math.abs(sellAndBuy.get(ADDED) - sellAndBuy.get(DECREASED));
        builder.append("result");
        builder.append(CSV_SEPARATOR);
        builder.append(value);
        builder.append(System.lineSeparator());
        String content = builder.toString().trim();
        return content;
    }

    public String writingFromFile(String toFileName, String content) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
        return toFileName;
    }
}
