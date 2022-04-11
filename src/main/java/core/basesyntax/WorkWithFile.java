package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> mapCSV = readingCSV(Path.of(fromFileName));
        List<String> result = new ArrayList<>();
        result.add("supply," + mapCSV.get("supply"));
        result.add("buy," + mapCSV.get("buy"));
        result.add("result," + (mapCSV.get("supply") - mapCSV.get("buy")));
        try {
            Files.write(Path.of(toFileName), result);
        } catch (IOException e) {
            throw new RuntimeException("Writing error", e);
        }
    }

    private Map<String, Integer> readingCSV(Path fromPath) {
        List<String> list = null;
        try {
            list = Files.readAllLines(fromPath);
        } catch (IOException e) {
            throw new RuntimeException("Reading error", e);
        }
        Map<String, Integer> mapCSV = new HashMap<>();
        for (String s : list) {
            String[] split = s.split(",");
            Integer value = Integer.parseInt(split[1]);
            mapCSV.put(split[0], mapCSV.containsKey(split[0]) ? mapCSV.get(split[0]) + value : value);
        }
        return mapCSV;
    }
}
