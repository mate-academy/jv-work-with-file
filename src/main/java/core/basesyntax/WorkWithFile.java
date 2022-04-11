package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> mapCsv;
        try {
            mapCsv = readingCsv(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Reading error", e);
        }
        List<String> result = new ArrayList<>();
        result.add("supply," + mapCsv.get("supply"));
        result.add("buy," + mapCsv.get("buy"));
        result.add("result," + (mapCsv.get("supply") - mapCsv.get("buy")));
        try {
            Files.write(Path.of(toFileName), result);
        } catch (IOException e) {
            throw new RuntimeException("Writing error", e);
        }
    }

    private Map<String, Integer> readingCsv(Path fromPath) throws IOException {
        List<String> lines = Files.readAllLines(fromPath);
        Map<String, Integer> mapCsv = new HashMap<>();
        for (String s : lines) {
            String[] split = s.split(",");
            Integer value = Integer.parseInt(split[1]);
            mapCsv.put(split[0], mapCsv.containsKey(split[0]) ?
                    mapCsv.get(split[0]) + value : value);
        }
        return mapCsv;
    }
}
