package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private final String supply = "supply";
    private final String buy = "buy";
    private final char csvSeparator = ',';
    private final int startIndex = 0;
    private final int stepIndex = 1;
    private final int startValue = 0;
    private Map<String, Integer> resultMap = new HashMap<>();

    public void getStatistic(String fromFileName, String toFileName) {
        resultMap.put(supply, startValue);
        resultMap.put(buy, startValue);
        readFile(fromFileName);
        writeFile(toFileName);
    }

    private void readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while (reader.ready()) {
                String lineInFile = reader.readLine();
                String operation = lineInFile
                        .substring(startIndex, lineInFile.indexOf(csvSeparator));
                String textValue = lineInFile
                        .substring(lineInFile.indexOf(csvSeparator) + stepIndex);
                Integer valueOperation = Integer.parseInt(textValue);
                resultMap.put(operation, (resultMap.get(operation) + valueOperation));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error!!! Can not read file.", e);
        }
    }

    private void writeFile(String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write("supply," + resultMap.get(supply) + System.lineSeparator()
                    + "buy," + resultMap.get(buy) + System.lineSeparator()
                    + "result," + (resultMap.get(supply) - resultMap.get(buy)));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error!!! Can not write file.", e);
        }
    }
}
