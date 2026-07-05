package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readData(fromFileName);
        String report = buildReport(data[0], data[1]);
        writeReport(toFileName, report);
    }

    private int[] readData(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if ("supply".equals(data[0])) {
                    supply += Integer.parseInt(data[1]);
                } else if ("buy".equals(data[0])) {
                    buy += Integer.parseInt(data[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return new int[]{supply, buy};
    }

    private String buildReport(int supply, int buy) {
        StringBuilder sb = new StringBuilder();
        sb.append("supply,").append(supply).append(System.lineSeparator());
        sb.append("buy,").append(buy).append(System.lineSeparator());
        sb.append("result,").append(supply - buy);
        return sb.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file " + toFileName, e);
        }
    }
}
