package core.basesyntax;

import java.util.Map;

public class MapValueReplacer {
    public void replaceOldAmountValueMap(Map<String, Integer> inputMap,
                                         String key,
                                         Integer newAmountValue) {
        for (var entry : inputMap.entrySet()) {
            if (entry.getKey().equals(key)) {
                Integer oldAmountValue = entry.getValue();
                inputMap.replace(key, oldAmountValue, newAmountValue + oldAmountValue);
            }
        }
    }
}
