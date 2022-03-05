package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] strings = s.split(",");
                if (hashMap.containsKey(strings[0])) {
                    hashMap.put(strings[0], Integer.valueOf(hashMap.get(strings[0])) + Integer.valueOf(strings[1]));
                } else {
                    hashMap.put(strings[0], Integer.valueOf(strings[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File does not exist: " + fromFileName, e);
        }
        hashMap.put("result", hashMap.get("supply") - hashMap.get("buy"));
        saveToFile(toFileName, getFormater(hashMap));
    }

    private String getFormater(HashMap<String, Integer> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry entry : hashMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
        }
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