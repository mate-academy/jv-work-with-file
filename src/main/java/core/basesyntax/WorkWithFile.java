package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> resultMap;
        try {
            File file = new File(fromFileName);
            List<String> lines = Files.readAllLines(file.toPath());
            resultMap = getResultMap(lines);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        if (resultMap.containsKey(BUY) || resultMap.containsKey(SUPPLY)) {
            writeFile(resultMap, toFileName);
        } else {
            System.out.println("Cant`t write to file, because not found results from file");
        }
    }

    private void writeFile(Map<String, Integer> resultMap, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            List<String> resultList = new ArrayList<>();
            for (String key : resultMap.keySet()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(key);
                stringBuilder.append(",");
                stringBuilder.append(resultMap.get(key).toString());
                resultList.add(stringBuilder.toString());
            }
            resultList.sort(Collections.reverseOrder());
            for (String line : resultList) {
                writer.write(line);
                writer.newLine();
            }
            int result = resultMap.get(SUPPLY) - resultMap.get(BUY);
            writer.write(RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
    }

    private List<String> getAllFieldNames(List<String> lines) {
        List<String> names = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(",");
            String firstWord = words[0].replaceAll("[\\p{Punct}]", "");
            names.add(firstWord);
        }
        return names;
    }

    private Map<String, Integer> getResultMap(List<String> lines) {
        Map<String, Integer> resultMap = new HashMap<>();
        List<String> names = getAllFieldNames(lines);
        for (String name : names) {
            resultMap.put(name, 0);
        }
        try {
            for (String line : lines) {
                String[] splitLine = line.split(",");
                int currentCount = resultMap.get(splitLine[0]);
                for (int i = 1; i < splitLine.length; i++) {
                    currentCount += Integer.parseInt(splitLine[i]);
                }
                resultMap.put(splitLine[0], currentCount);
            }
            return resultMap;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error while formatting numbers", e);
        }
    }

}
