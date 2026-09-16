package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        Map<String, Integer> map = initMap();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] strings = line.split(",");
                map.put(strings[0], map.getOrDefault(strings[0], 0)
                        + Integer.parseInt(strings[1]));
                line = bufferedReader.readLine();
            }
            map.put("result", map.getOrDefault("supply", 0)
                    - map.getOrDefault("buy", 0));
            bufferedWriter.write("supply," + map.getOrDefault("supply", 0)
                    + System.lineSeparator());
            bufferedWriter.write("buy," + map.getOrDefault("buy", 0)
                    + System.lineSeparator());
            bufferedWriter.write("result," + map.getOrDefault("result", 0)
                    + System.lineSeparator());

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read line from file", e);
        }
    }

    private Map<String, Integer> initMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("supply", 0);
        map.put("buy", 0);
        return map;
    }
}
