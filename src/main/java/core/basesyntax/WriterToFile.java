package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriterToFile {
    private static final String WRITE_RESULT_STRING = "result";

    public void writeToFile(Map<String, Integer> inputMap, String toFileName) {
        int index = 0;
        int supplyValue = 0;
        int buyValue = 0;
        int resultValue;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (var entry : inputMap.entrySet()) {
                bufferedWriter.write(entry.getKey()
                                             + FileParser.COMMA_SEPARATOR
                                             + entry.getValue() + System.lineSeparator());
                if (index == 0) {
                    supplyValue = entry.getValue();
                    index++;
                } else if (index == 1) {
                    buyValue = entry.getValue();
                }
            }
            resultValue = supplyValue - buyValue;
            bufferedWriter.write(WRITE_RESULT_STRING + FileParser.COMMA_SEPARATOR + resultValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
