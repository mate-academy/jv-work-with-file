package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {

    private final char comma = ',';
    private final String supply = "supply";
    private final String buy = "buy";
    private final String result = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = getStringFromFile(fromFileName);
        Map<String, Integer> map = calculateAndFormReport(lines);
        writeDataToFile(map, toFileName);
    }

    private List<String> getStringFromFile(String fromFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while (bufferedReader.ready()) {
                lines.add(bufferedReader.readLine());
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from .csv file " + fromFileName, e);
        }
    }

    private Map<String, Integer> calculateAndFormReport(List<String> lines) {
        int supplyCounter = 0;
        int buyCounter = 0;
        int addingNumber;
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String string : lines) {
            addingNumber = Integer.parseInt(string.substring(string.indexOf(comma) + 1));
            if (string.contains(supply)) {
                supplyCounter += addingNumber;
            } else {
                buyCounter += addingNumber;
            }
        }
        map.put(supply, supplyCounter);
        map.put(buy, buyCounter);
        map.put(result, supplyCounter - buyCounter);
        return map;
    }

    private void writeDataToFile(Map<String, Integer> reportValues, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (Map.Entry<String, Integer> entry : reportValues.entrySet()) {
                bufferedWriter.write(entry.getKey() + comma + entry.getValue()
                        + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to .csv file " + toFileName, e);
        }
    }
}
