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
        List<String> lines;
        try {
            //1. read from file, return List
            lines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        //2. process List and form report, return report
        List<String> report = getReport(lines);
        try {
            //3. write to file
            Files.write(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private List<String> getReport(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : lines) {
            String[] split = s.split(",");
            Integer value = Integer.parseInt(split[1]);
            map.put(split[0], map.containsKey(split[0]) ? map.get(split[0]) + value : value);
        }
        List<String> report = new ArrayList<>();
        report.add("supply," + map.get("supply"));
        report.add("buy," + map.get("buy"));
        report.add("result," + (map.get("supply") - map.get("buy")));
        return report;
    }
}
