package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_WORD = "buy";
    private static final String SUPPLY_WORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        String[] dataFromFile = readData(fromFileName).split(System.lineSeparator());
        for (String data: dataFromFile) {
            String[] splitData = data.split(",");
            if (splitData[0].equals(SUPPLY_WORD)) {
                supply += Integer.parseInt(splitData[1]);
            }
            if (splitData[0].equals(BUY_WORD)) {
                buy += Integer.parseInt(splitData[1]);
            }
        }
        String reportedString = makeReport(buy, supply);
        writeData(toFileName, reportedString);
    }

    private String readData(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private String makeReport(int buy, int supply) {
        return new StringBuilder(SUPPLY_WORD).append(",").append(supply)
                .append(System.lineSeparator())
                .append(BUY_WORD).append(",").append(buy)
                .append(System.lineSeparator())
                .append("result").append(",")
                .append(supply - buy).toString();
    }

    private void writeData(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
