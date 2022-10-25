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
    private static final String NEW_LINE = System.lineSeparator();
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final Map<String, Integer> MAP = new TreeMap<>(Comparator.reverseOrder());

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
        for (String row : data) {
            String[] split = row.split(",");
            int amount = Integer.parseInt(split[AMOUNT_INDEX]);
            String operationType = split[OPERATION_TYPE_INDEX];
            if (MAP.containsKey(operationType)) {
                MAP.replace(operationType, MAP.get(operationType) + amount);
            }
            MAP.putIfAbsent(operationType, amount);
        }
        int supplyAmount = MAP.get("supply");
        int buyAmount = MAP.get("buy");
        int resultAmount = supplyAmount - buyAmount;
        for (String operation : MAP.keySet()) {
            STRING_BUILDER.append(operation)
                    .append(",")
                    .append(MAP.get(operation))
                    .append(NEW_LINE);
        }
        STRING_BUILDER.append("result,").append(resultAmount);
        return STRING_BUILDER.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
