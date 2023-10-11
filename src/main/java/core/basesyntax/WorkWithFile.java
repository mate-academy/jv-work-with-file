package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private Map<String, Integer> category;

    public void getStatistic(String fromFileName, String toFileName) {
        category = new HashMap<>();
        for (String line : getDataFromFile(fromFileName)) {
            String[] parts = line.split(",");
            calculateTotalAmount(parts[0], Integer.parseInt(parts[1]));
        }
        StringBuilder result = new StringBuilder();
        result.append("supply,")
                .append(category.get("supply"))
                .append(System.lineSeparator());
        result.append("buy,")
                .append(category.get("buy"))
                .append(System.lineSeparator());
        result.append("result,")
                .append(category.get("supply") - category.get("buy"));
        writeDataToFile(toFileName, result.toString());
    }

    private List<String> getDataFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8);
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

    private void calculateTotalAmount(String operationType, int amount) {
        int totalAmount = category.getOrDefault(operationType, 0);
        totalAmount += amount;
        category.put(operationType, totalAmount);
    }
}
