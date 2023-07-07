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

    private Map<String, Integer> readFile(String fileName) {
        File fromFile = new File(fileName);
        Map<String, Integer> resultMap = new LinkedHashMap<>();
        resultMap.put("supply", ZERO_NUMBER);
        resultMap.put("buy", ZERO_NUMBER);
        resultMap.put("result", ZERO_NUMBER);

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            String[] valueStringArray;
            while (value != null) {
                valueStringArray = value.split(",");

                if (resultMap.containsKey(valueStringArray[MAP_INDEX_KEY])) {
                    int sumNumbers = resultMap.get(valueStringArray[MAP_INDEX_KEY]);
                    resultMap.put(valueStringArray[MAP_INDEX_KEY],
                            (sumNumbers + Integer.parseInt(valueStringArray[MAP_INDEX_VALUE])));
                } else {
                    resultMap.put(valueStringArray[MAP_INDEX_KEY],
                            Integer.parseInt(valueStringArray[MAP_INDEX_VALUE]));
                }

                value = reader.readLine();
            }
            resultMap.put("result", resultMap.get("supply") - resultMap.get("buy"));

        } catch (IOException e) {
            throw new RuntimeException("Cant open the file " + fileName, e);
        }
        return resultMap;
    }

    private void writeFile(String fileName, Map<String, Integer> resultMap) {
        File toFile = new File(fileName);

        try (BufferedWriter write = new BufferedWriter(new FileWriter(toFile))) {
            for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
                String lineToWrite = entry.getKey() + "," + entry.getValue();
                write.write(lineToWrite);
                write.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant open the file " + fileName, e);
        }
    }
}
