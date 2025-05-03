package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(",");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);

        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        int buy = 0;
        int supply = 0;
        StringBuilder builder = new StringBuilder("supply,");
        String[] lines = data.split(",");
        for (int i = 0; i < lines.length; i = i + 2) {
            if (lines[i].equals("buy")) {
                buy = buy + Integer.parseInt(lines[i + 1]);
            } else if (lines[i].equals("supply")) {
                supply = supply + Integer.parseInt(lines[i + 1]);
            }
        }
        int result = supply - buy;
        return builder.append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator()).append("result,")
                .append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Could`t write a file", e);
        }
    }
}
