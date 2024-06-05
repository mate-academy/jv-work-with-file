package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class FileParser {
    public static final String COMMA_SEPARATOR = ",";
    private final MapChecker mapChecker = new MapChecker();
    private final MapValueReplacer valueReplacer = new MapValueReplacer();

    public Map<String, Integer> parseFileContent(String fileName) {
        Map<String, Integer> contentMap = new TreeMap<>(Comparator.reverseOrder());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLines = line.split(COMMA_SEPARATOR);
                String currentOperation = splitLines[0];
                Integer currentAmount = Integer.parseInt(splitLines[1]);
                if (mapChecker.checkMapForExistKey(
                        contentMap, currentOperation)) {

                    valueReplacer.replaceOldAmountValueMap(contentMap,
                                                           currentOperation,
                                                           currentAmount);
                } else {
                    contentMap.put(currentOperation, currentAmount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contentMap;
    }

}
