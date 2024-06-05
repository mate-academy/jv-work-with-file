package core.basesyntax;

import java.util.Map;

public class MapChecker {
    public boolean checkMapForExistKey(Map<String, Integer> inputMap, String key) {
        for (var entry : inputMap.entrySet()) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
}
