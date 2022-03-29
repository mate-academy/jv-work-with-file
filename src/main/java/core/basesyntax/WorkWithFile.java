package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUYWORD = "buy";
    private static final String SUPPLYWORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        String[] dataFromFile = readData(fromFileName).split(System.lineSeparator());
        for (String data: dataFromFile) {
            if (data.substring(0,data.indexOf(",")).equals(SUPPLYWORD)) {
                supply += Integer.parseInt(data.substring(data.indexOf(",") + 1));
            }
            if (data.substring(0,data.indexOf(",")).equals(BUYWORD)) {
                buy += Integer.parseInt(data.substring(data.indexOf(",") + 1));
            }
        }
        String reportedString = makeReport(buy, supply);
        writeData(toFileName, reportedString);
    }

    public String readData(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return builder.toString();
    }

    private String makeReport(int buy, int supply) {
        return new StringBuilder(SUPPLYWORD).append(",").append(supply)
                .append(System.lineSeparator())
                .append(BUYWORD).append(",").append(buy)
                .append(System.lineSeparator())
                .append("result").append(",")
                .append(supply - buy).toString();
    }

    private void writeData(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file");
        }
    }
}
