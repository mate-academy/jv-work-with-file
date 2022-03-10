package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> hashMap = new LinkedHashMap<>();
        File file = new File(fromFileName);
        List<String> stringList = null;
        try {
            stringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("File does not exist: " + fromFileName, e);
        }
        for (String s : stringList) {
            String[] arr = s.split(",");
            if (hashMap.containsKey(arr[0])) {
                hashMap.put(arr[0], hashMap.get(arr[0]) + Integer.valueOf(arr[1]));
            } else {
                hashMap.put(arr[0], Integer.valueOf(arr[1]));
            }
        }
        hashMap.put("result", hashMap.get("supply") - hashMap.get("buy"));
        saveToFile(toFileName, getFormater(hashMap));
    }

    private String getFormater(Map<String, Integer> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> stringBuilder
                        .append(entry.getKey()).append(",")
                        .append(entry.getValue()).append(System.lineSeparator()));

        return stringBuilder.toString();
    }

    private void saveToFile(String toFileName, String string) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file: " + toFileName, e);
        }

        try {
            Files.write(Paths.get(file.getPath()), Collections.singleton(string));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: " + toFileName, e);
        }
    }
}
