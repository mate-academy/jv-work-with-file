package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            Map<String, Integer> map = reader.lines()
                    .collect(Collectors.groupingBy(str -> str.split(",")[0],
                    Collectors.reducing(0, str -> Integer.valueOf(str.split(",")[1]),
                            Integer::sum)));
            writer.write("supply," + map.get("supply"));
            writer.newLine();
            writer.write("buy," + map.get("buy"));
            writer.newLine();
            writer.write("result," + (map.get("supply") - map.get("buy")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
