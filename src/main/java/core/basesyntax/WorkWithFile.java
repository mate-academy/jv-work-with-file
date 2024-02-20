package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final int INDEX_OPERATION_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> content = getFileContent(fromFileName);
        String report = createReport(content);
        writeToFile(toFileName, report);
    }

    private List<String> getFileContent(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
    }

    private String createReport(List<String> data) {
        HashMap<String, Integer> resultingMap = new HashMap<>();
        for (String listItem : data) {
            String[] row = listItem.split(COMMA);
            String operationType = row[INDEX_OPERATION_TYPE];
            Integer amount = Integer.valueOf(row[INDEX_AMOUNT]);
            resultingMap.put(operationType, resultingMap.getOrDefault(operationType, 0) + amount);
        }
        return SUPPLY + COMMA + resultingMap.get(SUPPLY) + System.lineSeparator()
                + BUY + COMMA + resultingMap.get(BUY) + System.lineSeparator()
                + RESULT + COMMA + (resultingMap.get(SUPPLY) - resultingMap.get(BUY));
    }

    private void writeToFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
