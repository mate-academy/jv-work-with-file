package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private final Map<String, String> statisticsCache = new HashMap<>();

    public int[] readStatistic(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[0].equals("supply")) {
                    supply += Integer.parseInt(split[1]);
                } else if (split[0].equals("buy")) {
                    buy += Integer.parseInt(split[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supply, buy};
    }

    public StringBuilder countStatistic(String fromFileName) {
        int[] stats = readStatistic(fromFileName);
        int supply = stats[0];
        int buy = stats[1];
        int countedResult = supply - buy;

        StringBuilder result = new StringBuilder();
        result.append("supply,");
        result.append(supply);
        result.append("\r\n");
        result.append("buy,");
        result.append(buy);
        result.append("\r\n");
        result.append("result,");
        result.append(countedResult);

        return result;
    }

    public void writeStatistic(String fromFileName, String newFile) {
        StringBuilder result = countStatistic(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile))) {
            bufferedWriter.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    public String getStatistic(String fromFileName, String newFile) {
        if (statisticsCache.containsKey(fromFileName)) {
            return statisticsCache.get(fromFileName);
        }

        writeStatistic(fromFileName, newFile);
        String result = countStatistic(fromFileName).toString();
        statisticsCache.put(fromFileName, result);
        return result;
    }
}
