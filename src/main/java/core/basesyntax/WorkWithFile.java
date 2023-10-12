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
    private static final int INDEX_OPERATION_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;
    private Map<String, Integer> category = new HashMap<>();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private void writeToFile(String data, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private void calculateTotalAmount(String operationType, int amount) {
        int totalAmount = category.getOrDefault(operationType, 0);
        totalAmount += amount;
        category.put(operationType, totalAmount);
    }

    private String createReport(List<String> data) {
        category = new HashMap<>();
        for (String line : data) {
            String[] parts = line.split(",");
            calculateTotalAmount(parts[INDEX_OPERATION_TYPE],
                    Integer.parseInt(parts[INDEX_AMOUNT]));
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
        return result.toString();
    }
}
