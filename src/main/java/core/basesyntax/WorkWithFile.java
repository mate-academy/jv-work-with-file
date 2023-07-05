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
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        Map<String, Integer> resultFileMap = new LinkedHashMap<>();
        resultFileMap.put("supply", ZERO_NUMBER);
        resultFileMap.put("buy", ZERO_NUMBER);
        resultFileMap.put("result", ZERO_NUMBER);

        try (BufferedReader readerFrom = new BufferedReader(new FileReader(fromFile));
                BufferedWriter writeTo = new BufferedWriter(new FileWriter(toFile))) {
            String value = readerFrom.readLine();

            while (value != null) {
                String[] valueStringArray = value.split(",");

                if (resultFileMap.containsKey(valueStringArray[MAP_INDEX_KEY])) {
                    int sumNumbers = resultFileMap.get(valueStringArray[MAP_INDEX_KEY]);
                    resultFileMap.put(valueStringArray[MAP_INDEX_KEY],
                            (sumNumbers + Integer.parseInt(valueStringArray[1])));
                } else {
                    resultFileMap.put(valueStringArray[MAP_INDEX_KEY],
                            Integer.parseInt(valueStringArray[MAP_INDEX_VALUE]));
                }

                value = readerFrom.readLine();
            }
            resultFileMap.put("result", resultFileMap.get("supply") - resultFileMap.get("buy"));

            for (Map.Entry<String, Integer> entry : resultFileMap.entrySet()) {
                String lineToWrite = entry.getKey() + "," + entry.getValue();
                writeTo.write(lineToWrite);
                writeTo.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant open the file.", e);
        }

    }
}
