package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public List<String> readFromFile(String fromFileName) {
        Path filePath = Paths.get(fromFileName);
        try {
            return Files.readAllLines(filePath);
        } catch (IOException a) {
            throw new RuntimeException("Something gone wrong to read the file: " + fromFileName);
        }
    }

    public void writeToFile(String toFileName, String report) {
        Path filePath = Paths.get(toFileName);
        try {
            Files.writeString(filePath, report);
        } catch (IOException e) {
            throw new RuntimeException("Something gone wrong to write to the file: " + toFileName);
        }
    }

    public String createReport(List<String> data) {
        Map<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        StringBuilder builder = new StringBuilder();
        for (String row : data) {
            String[] split = row.split(",");

            int amount = Integer.parseInt(split[1]);
            String operationType = split[0];
            if (map.containsKey(operationType)) {
                map.replace(operationType, map.get(operationType) + amount);
            }
            map.putIfAbsent(operationType, amount);
        }
        int supplyAmount = map.get("supply");
        int buyAmount = map.get("buy");
        int resultAmount = supplyAmount - buyAmount;
        for (String key : map.keySet()) {
            builder.append(key)
                    .append(",")
                    .append(map.get(key))
                    .append(System.lineSeparator());
        }
        builder.append("result,").append(resultAmount);
        return builder.toString();
    }
}
