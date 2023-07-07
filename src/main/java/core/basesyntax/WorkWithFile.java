package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkWithFile {
    public static final int ZERO_NUMBER = 0;
    public static final int MAP_INDEX_KEY = 0;
    public static final int MAP_INDEX_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> resultFileMap = readFile(fromFileName);
        writeFile(toFileName, resultFileMap);
    }

    public Map<String, Integer> readFile(String fileName) {
        File fromFile = new File(fileName);
        Map<String, Integer> fileMap = new LinkedHashMap<>();
        fileMap.put("supply", ZERO_NUMBER);
        fileMap.put("buy", ZERO_NUMBER);
        fileMap.put("result", ZERO_NUMBER);

        try (BufferedReader readerFrom = new BufferedReader(new FileReader(fromFile))) {
            String value = readerFrom.readLine();

            while (value != null) {
                String[] valueStringArray = value.split(",");

                if (fileMap.containsKey(valueStringArray[MAP_INDEX_KEY])) {
                    int sumNumbers = fileMap.get(valueStringArray[MAP_INDEX_KEY]);
                    fileMap.put(valueStringArray[MAP_INDEX_KEY],
                            (sumNumbers + Integer.parseInt(valueStringArray[1])));
                } else {
                    fileMap.put(valueStringArray[MAP_INDEX_KEY],
                            Integer.parseInt(valueStringArray[MAP_INDEX_VALUE]));
                }

                value = readerFrom.readLine();
            }
            fileMap.put("result", fileMap.get("supply") - fileMap.get("buy"));

        } catch (IOException e) {
            throw new RuntimeException("Cant open the file " + fileName, e);
        }
        return fileMap;
    }

    public void writeFile(String fileName, Map<String, Integer> fileMap) {
        File toFile = new File(fileName);

        try (BufferedWriter writeTo = new BufferedWriter(new FileWriter(toFile))) {
            for (Map.Entry<String, Integer> entry : fileMap.entrySet()) {
                String lineToWrite = entry.getKey() + "," + entry.getValue();
                writeTo.write(lineToWrite);
                writeTo.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant open the file " + fileName, e);
        }
    }
}
