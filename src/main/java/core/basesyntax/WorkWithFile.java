package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);

    }

    public List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException a) {
            throw new RuntimeException("Something gone wrong to read the file");
        }
    }

    public void writeToFile(String toFileName, String report) {
        try {
            Files.writeString(Paths.get(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Somethin gone wrong to write to the file");
        }
    }

    public String createReport(List<String> data) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (String row : data) {
            String[] split = row.split(",");
            int amount = Integer.parseInt(split[1]);
            String operationType = split[0];
            if (map.containsKey(operationType)) {
                map.put(operationType, map.get(operationType) + amount);
            }
            map.put(operationType, amount);
        }
        for (String key : map.keySet()) {
            builder.append(key)
                    .append(",")
                    .append(map.get(key))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
