package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readData(fromFileName);
        int[] statistics = calculateStatistics(data);
        String report = getReport(statistics);
        writeToFile(report, toFileName);
    }

    private List<String> readData(String fromFileName) {
        List<String> data = new ArrayList<>();

        File sourceFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String nextString;
            while ((nextString = reader.readLine()) != null) {
                data.add(nextString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    private int[] calculateStatistics(List<String> data) {
        int supply = 0;
        int buy = 0;

        for (String line : data) {
            String[] lineValue = line.split(",");
            int amount = Integer.parseInt(lineValue[VALUE_INDEX].trim());
            if (lineValue[KEY_INDEX].equals(SUPPLY)) {
                supply += amount;
            } else if (lineValue[KEY_INDEX].equals(BUY)) {
                buy += amount;
            }
        }

        int[] statistics = new int[]{supply, buy};
        return statistics;
    }

    private String getReport(int[] statistics) {
        int supply = statistics[0];
        int buy = statistics[1];

        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator());
        report.append("buy,").append(buy).append(System.lineSeparator());
        report.append("result,").append(supply - buy);

        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File targetFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
