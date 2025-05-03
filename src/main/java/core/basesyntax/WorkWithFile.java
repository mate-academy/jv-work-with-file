package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String FILE_READING_EXCEPTION_MESSAGE = "Failed to read the file";
    private static final String FILE_WRITING_EXCEPTION_MESSAGE = "Failed to write data to the file";
    private static final String COMA_REGEX = ",";
    private static final String BUY_MAP_KEY = "buy";
    private static final String SUPPLY_MAP_KEY = "supply";
    private static final String RESULT_MAP_KEY = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> listOfLines = readFile(fromFileName);
        LinkedHashMap<String, Integer> calculatedData = calculateData(listOfLines);
        String generatedReport = generateReport(calculatedData);
        writeToFile(toFileName, generatedReport);
    }

    private List<String> readFile(String sourceFileName) {
        Path path = Paths.get(sourceFileName);
        List<String> listOfLines = null;
        try {
            listOfLines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(FILE_READING_EXCEPTION_MESSAGE + e);
        }
        return listOfLines;
    }

    private LinkedHashMap<String, Integer> calculateData(List<String> listOfLines) {
        LinkedHashMap<String, Integer> mapOfData = new LinkedHashMap<>();
        mapOfData.put(SUPPLY_MAP_KEY, 0);
        mapOfData.put(BUY_MAP_KEY, 0);
        String operation;
        Integer amount;

        for (String line : listOfLines) {
            operation = line.split(COMA_REGEX)[OPERATION_TYPE_INDEX];
            amount = Integer.parseInt(line.split(COMA_REGEX)[OPERATION_AMOUNT_INDEX]);
            if (mapOfData.containsKey(operation)) {
                mapOfData.put(operation, mapOfData.get(operation) + amount);
            }
        }
        mapOfData.put(RESULT_MAP_KEY, mapOfData.get(SUPPLY_MAP_KEY) - mapOfData.get(BUY_MAP_KEY));
        return mapOfData;
    }

    private String generateReport(HashMap<String, Integer> mapOfData) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : mapOfData.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(COMA_REGEX)
                    .append(entry.getValue());
            if (!entry.getKey().equals(RESULT_MAP_KEY)) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String destinationFileName, String reportString) {
        Path path = Paths.get(destinationFileName);
        try {
            Files.write(path, reportString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(FILE_WRITING_EXCEPTION_MESSAGE + e);
        }
    }
}
