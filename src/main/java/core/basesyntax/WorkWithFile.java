package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> mapCsv;
        try {
            mapCsv = readingCsv(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        List<String> result = new ArrayList<>();
        result.add(SUPPLY + "," + mapCsv.get(SUPPLY));
        result.add(BUY + "," + mapCsv.get(BUY));
        result.add(RESULT + "," + (mapCsv.get(SUPPLY) - mapCsv.get(BUY)));
        try {
            Files.write(Path.of(toFileName), result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private Map<String, Integer> readingCsv(Path fromPath) throws IOException {
        List<String> lines = Files.readAllLines(fromPath);
        Map<String, Integer> mapCsv = new HashMap<>();
        for (String s : lines) {
            String[] split = s.split(",");
            Integer value = Integer.parseInt(split[1]);
            mapCsv.put(split[0], mapCsv.containsKey(split[0])
                    ? mapCsv.get(split[0]) + value : value);
        }
        return mapCsv;
    }
}
