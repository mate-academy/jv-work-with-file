package core.basesyntax;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        final HashMap<String, Integer> map = new HashMap<>();
        final StringBuilder stringBuilder = new StringBuilder();
        final List<String> inputList = Utils.getFile(fromFileName).collect(Collectors.toList());
        map.put(SUPPLY, 0);
        map.put(BUY, 0);
        map.put(RESULT, 0);
        for (String s : inputList) {
            String[] arr = s.split(",");
            map.put(arr[0], map.get(arr[0]) + Integer.parseInt(arr[1]));
        }
        map.put(RESULT, map.get(SUPPLY) - map.get(BUY));
        stringBuilder.append(SUPPLY).append(",").append(map.get(SUPPLY))
                .append(System.lineSeparator())
                .append(BUY).append(",").append(map.get(BUY))
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(map.get(RESULT));
        Utils.writeToFile(toFileName, stringBuilder.toString().trim());
    }
}
