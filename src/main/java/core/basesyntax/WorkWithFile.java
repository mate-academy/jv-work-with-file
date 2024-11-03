package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> resultMap;
        try {
            File file = new File(fromFileName);
            List<String> lines = Files.readAllLines(file.toPath());
            resultMap = getResultMap(lines);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String key : resultMap.keySet()) {
                writer.write(key);
                writer.write(",");
                writer.write(resultMap.get(key).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }

    }

    public List<String> getAllFieldNames(List<String> lines) {
        List<String> names = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(",");
            String firstWord = words[0].replaceAll("[\\p{Punct}]", "");
            names.add(firstWord);
        }
        return names;
    }

    public Map<String, Integer> getResultMap(List<String> lines) {
        Map<String, Integer> resultMap = new HashMap<>();
        List<String> names = getAllFieldNames(lines);
        for (String name : names) {
            resultMap.put(name, 0);
        }
        for (String line : lines) {
            String[] splitLine = line.split(",");
            if (names.contains(splitLine[0])) {
                int currentCount = resultMap.get(splitLine[0]);
                for (int i = 1; i < splitLine.length; i++) {
                    currentCount += Integer.parseInt(splitLine[i]);
                }
                resultMap.put(splitLine[0], currentCount);
            }
        }
        return resultMap;
    }

}
