package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            Map<String, Integer> map = reader.lines().collect(Collectors
                    .groupingBy(str -> str.split(",")[FIRST_INDEX],
                    Collectors.reducing(0, str -> Integer.valueOf(str.split(",")[SECOND_INDEX]),
                            Integer::sum)));
            String report = "supply," + map.get("supply") + System.lineSeparator()
                    + "buy," + map.get("buy") + System.lineSeparator()
                    + "result," + (map.get("supply") - map.get("buy"));
            writer.write(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t read this file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
