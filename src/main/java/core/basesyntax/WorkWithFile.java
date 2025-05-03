package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
            return data.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file", e);
        }
    }

    private String createReport(String dataFromFile) {
        int[] statistics = calculateStatistics(dataFromFile);
        return SUPPLY + ',' + statistics[SUPPLY_INDEX] + System.lineSeparator()
                + BUY + ',' + statistics[BUY_INDEX] + System.lineSeparator()
                + RESULT + ',' + (statistics[SUPPLY_INDEX] - statistics[BUY_INDEX]);
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    private int[] calculateStatistics(String dataFromFile) {
        int supply = 0;
        int buy = 0;

        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] values = line.split(",");
            int amount = Integer.parseInt(values[1]);
            if (SUPPLY.equals(values[0])) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        return new int[]{supply, buy};
    }
}
