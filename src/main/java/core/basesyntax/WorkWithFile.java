package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

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
        final int[] STATISTICS = calculateStatistics(dataFromFile);
        return "supply," + STATISTICS[0] + System.lineSeparator()
                + "buy," + STATISTICS[1] + System.lineSeparator()
                + "result," + (STATISTICS[0] - STATISTICS[1]);
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
            if ("supply".equals(values[0])) {
                supply += amount;
            } else {
                buy += amount;
            }
        }

        return new int[]{supply, buy};
    }
}
