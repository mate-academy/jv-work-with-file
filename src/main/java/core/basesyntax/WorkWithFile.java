package core.basesyntax;

import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String CHAR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        FileService fileService = new FileService();
        final List<String> inputList = fileService.readFromFile(fromFileName);
        final StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        map.put(SUPPLY, 0);
        map.put(BUY, 0);
        for (String s : inputList) {
            String[] arr = s.split(CHAR);
            map.put(arr[0], map.get(arr[0]) + Integer.parseInt(arr[1]));
        }
        map.put(RESULT, map.get(SUPPLY) - map.get(BUY));
        stringBuilder.append(SUPPLY).append(CHAR).append(map.get(SUPPLY))
                .append(System.lineSeparator())
                .append(BUY).append(CHAR).append(map.get(BUY))
                .append(System.lineSeparator())
                .append(RESULT).append(CHAR).append(map.get(RESULT));
        fileService.writeToFile(toFileName, stringBuilder.toString().trim());
    }
}
