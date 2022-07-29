package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkWithFile {
    private static int FIRST_INDEX = 0;
    private static int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> map;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            map = reader.lines().collect(Collectors.groupingBy(str -> str.split(",")[FIRST_INDEX],
                    Collectors.reducing(0, str -> Integer.valueOf(str.split(",")[SECOND_INDEX]),
                            Integer::sum)));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read this file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + map.get("supply"));
            writer.newLine();
            writer.write("buy," + map.get("buy"));
            writer.newLine();
            writer.write("result," + (map.get("supply") - map.get("buy")));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
