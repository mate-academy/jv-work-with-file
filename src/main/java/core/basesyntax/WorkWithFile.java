package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        readFile(fromFileName, stringBuilder);
        createMapOfDataFromFile(stringBuilder, map);
        writeFile(toFileName, map, getResultFromMap(map));
    }

    private static int getResultFromMap(Map<String, Integer> map) {
        int resultSupply = map.get("supply");
        int resultBuy = map.get("buy");
        return resultSupply - resultBuy;
    }

    private static void createMapOfDataFromFile(StringBuilder stringBuilder,
                                                Map<String, Integer> map) {
        String[] split = stringBuilder.toString().split("\n");
        for (String string : split) {
            String[] subString = string.split(",");
            map.merge(subString[0], Integer.parseInt(subString[1]), Integer::sum);
        }
    }

    private static void writeFile(String fileTo, Map<String, Integer> map, int result) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo));
            for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                bufferedWriter.write(stringIntegerEntry.getKey() + ","
                        + stringIntegerEntry.getValue() + "\n");
            }
            bufferedWriter.write("result," + result);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write the file", e);
        }
    }

    private static void readFile(String fileFrom, StringBuilder stringBuilder) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find the file", e);
        }
    }
}

