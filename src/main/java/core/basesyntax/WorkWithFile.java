package core.basesyntax;

import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        final StringBuilder stringBuilder = new StringBuilder();
        FileService fileService = new FileService();
        List<String> inputList = fileService.readFromFile(fromFileName);
        HashMap<String, Integer> map = new HashMap<>();
        map.put(SUPPLY, 0);
        map.put(BUY, 0);
        for (String string : inputList) {
            String[] splittedData = string.split(COMMA);
            map.put(splittedData[0], map.get(splittedData[0]) + Integer.parseInt(splittedData[1]));
        }
        map.put(RESULT, map.get(SUPPLY) - map.get(BUY));
        stringBuilder.append(SUPPLY).append(COMMA).append(map.get(SUPPLY))
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(map.get(BUY))
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(map.get(RESULT));
        fileService.writeToFile(toFileName, stringBuilder.toString().trim());
    }
}
