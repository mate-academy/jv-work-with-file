package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        this.writeDataToFile(toFileName, this.prepareData(this.gatherStats(fromFileName)));
    }

    private String prepareData(int[] stats) {
        int result = stats[0] - stats[1];
        return "supply,"
                + stats[0]
                + System.lineSeparator()
                + "buy,"
                + stats[1]
                + System.lineSeparator()
                + "result,"
                + result;
    }

    private void writeDataToFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private int[] gatherStats(String fromFileName) {
        int[] stats = new int[2];

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                stats[split[0].equals("buy") ? 1 : 0] += Integer.parseInt(split[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(fromFileName + " not found", e);
        }

        return stats;
    }
}
