package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY_ALIAS = "supply";
    private static final String BUY_ALIAS = "buy";
    private static final String RESULT_ALIAS = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);

        try {
            List<String> fileInfo = Files.readAllLines(file.toPath());
            Map<String, Integer> result = validateFileInfo(fileInfo);
            writeResultToFile(result, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private Map<String, Integer> validateFileInfo(List<String> fileInfo) {
        Map<String, Integer> result = new LinkedHashMap<>();

        for (String fileInfoItem : fileInfo) {
            String[] splitFileInfoItem = fileInfoItem.toLowerCase().split(DELIMITER);
            String operation = splitFileInfoItem[OPERATION_INDEX];
            int value = Integer.parseInt(splitFileInfoItem[VALUE_INDEX]);

            if (result.containsKey(operation)) {
                result.computeIfPresent(operation, (k, v) -> v + value);
            } else {
                result.put(operation, value);
            }
        }

        int total = result.get(SUPPLY_ALIAS) - result.get(BUY_ALIAS);
        result.put(RESULT_ALIAS, total);

        return result;
    }

    private void writeResultToFile(Map<String, Integer> result, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)))) {
            StringBuilder resultToWrite = new StringBuilder();
            resultToWrite.append(SUPPLY_ALIAS).append(DELIMITER);
            resultToWrite.append(result.get(SUPPLY_ALIAS)).append(System.lineSeparator());
            resultToWrite.append(BUY_ALIAS).append(DELIMITER);
            resultToWrite.append(result.get(BUY_ALIAS)).append(System.lineSeparator());
            resultToWrite.append(RESULT_ALIAS).append(DELIMITER);
            resultToWrite.append(result.get(RESULT_ALIAS)).append(System.lineSeparator());
            writer.write(resultToWrite.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
