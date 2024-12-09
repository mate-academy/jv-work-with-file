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
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = getStringFromFile(fromFileName);
        Map<String, Integer> map = calculateAndFormReport(lines);
        writeDataToFile(map, toFileName);
    }

    private List<String> getStringFromFile(String fromFileName) {
        List<String> lines = new ArrayList<String>();
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
        int supply = 0;
        int buy = 0;
        int addingNumber;
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String string : lines) {
            addingNumber = Integer.parseInt(string.substring(string.indexOf(',') + 1));
            if (string.contains("supply")) {
                supply += addingNumber;
            } else {
                buy += addingNumber;
            }
        }
        map.put("supply", supply);
        map.put("buy", buy);
        map.put("result", supply - buy);
        return map;
    }

    private void writeDataToFile(Map<String, Integer> reportValues, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (Map.Entry<String, Integer> entry : reportValues.entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue()
                        + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to .csv file " + toFileName, e);
        }
    }
}
