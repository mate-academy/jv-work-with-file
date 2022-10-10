package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String STRING_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> map = fileToMap(fromFileName);
        mapToFile(map, toFileName);
    }

    private Map<String, Integer> fileToMap(String fileName) {
        Map<String, Integer> map = new HashMap<>();
        try (var reader = Files.lines(Paths.get(fileName))) {
            reader.forEach(x -> {
                String[] splitString = x.split(STRING_SEPARATOR);
                String key = splitString[OPERATION_INDEX];
                int amount = Integer.parseInt(splitString[AMOUNT_INDEX]);
                int amountInMap = map.getOrDefault(key, 0);
                map.put(key, amount + amountInMap);
            });
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong, when reading from " + fileName, e);
        }
        return map;
    }

    private void mapToFile(Map<String, Integer> map, String fileName) {
        try (var writer = Files.newBufferedWriter(Paths.get(fileName))) {
            int supplyAmount = map.get(SUPPLY);
            int buyAmount = map.get(BUY);
            int resultAmount = supplyAmount - buyAmount;
            writer.write(SUPPLY + STRING_SEPARATOR + supplyAmount);
            writer.newLine();
            writer.write(BUY + STRING_SEPARATOR + buyAmount);
            writer.newLine();
            writer.write(RESULT + STRING_SEPARATOR + resultAmount);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong, when writing to " + fileName, e);
        }
    }
}
