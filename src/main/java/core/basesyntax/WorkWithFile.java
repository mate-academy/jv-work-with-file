package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lineList = getDataFromFile(fromFileName);
        HashMap<String, Integer> category = new HashMap<>();
        for (String line : lineList) {
            String[] parts = line.split(",");
            String name = parts[0];
            int amount = Integer.parseInt(parts[1]);
            int totalAmount = category.getOrDefault(name, 0);
            totalAmount += amount;
            category.put(name, totalAmount);
        }
        StringBuilder result = new StringBuilder();
        result.append("supply")
                .append(",")
                .append(category.get("supply"))
                .append(System.lineSeparator());
        result.append("buy")
                .append(",")
                .append(category.get("buy"))
                .append(System.lineSeparator());
        result.append("result")
                .append(",")
                .append(category.get("supply") - category.get("buy"))
                .append(System.lineSeparator());
        writeDataToFile(toFileName, result.toString());
    }

    private List<String> getDataFromFile(String fileName) {
        try {
            List<String> lineList =
                    Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8);
            return lineList;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeDataToFile(String fileName, String data) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
