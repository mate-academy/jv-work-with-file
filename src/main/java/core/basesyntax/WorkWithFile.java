package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final char CSV_SEPARATOR = ',';
    private static final int START_INDEX = 0;
    private static final int STEP_INDEX = 1;
    private static final int START_VALUE = 0;
    private Map<String, Integer> resultMap = new HashMap<>();

    public void getStatistic(String fromFileName, String toFileName) {
        resultMap.put(SUPPLY_OPERATION, START_VALUE);
        resultMap.put(BUY_OPERATION, START_VALUE);
        readFile(fromFileName);
        writeFile(toFileName);
    }

    private void readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while (reader.ready()) {
                String lineInFile = reader.readLine();
                String operation = lineInFile
                        .substring(START_INDEX, lineInFile.indexOf(CSV_SEPARATOR));
                String textValue = lineInFile
                        .substring(lineInFile.indexOf(CSV_SEPARATOR) + STEP_INDEX);
                Integer valueOperation = Integer.parseInt(textValue);
                resultMap.put(operation, (resultMap.get(operation) + valueOperation));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error!!! Can not read file.", e);
        }
    }

    private void writeFile(String toFileName) {
        String result = "supply," + resultMap.get(SUPPLY_OPERATION) + System.lineSeparator()
                + "buy," + resultMap.get(BUY_OPERATION) + System.lineSeparator()
                + "result," + (resultMap.get(SUPPLY_OPERATION) - resultMap.get(BUY_OPERATION));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Error!!! Can not write file.", e);
        }
    }
}
