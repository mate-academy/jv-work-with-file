package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String newLine = System.lineSeparator();
    private final StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String result = calculateStatistic(readDataFromFile(fromFileName));
        writeDataToFile(result, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                builder.append(value).append(newLine);
                value = reader.readLine();
            }

            String inputInfo = builder.toString();
            builder.setLength(0);

            return inputInfo;
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }

    private void writeDataToFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a file", e);
        }
    }

    private String calculateStatistic(String inputInfo) {
        String[] separateInfo = inputInfo.split("\\W+");
        int supplies = 0;
        int buy = 0;

        for (int i = 0; i < separateInfo.length; i += 2) {
            if (separateInfo[i].startsWith("supply")) {
                supplies += Integer.parseInt(separateInfo[i + 1]);
            } else if (separateInfo[i].startsWith("buy")) {
                buy += Integer.parseInt(separateInfo[i + 1]);
            }
        }
        builder.append("supply,").append(supplies).append(newLine).append("buy,").append(buy)
                .append(newLine).append("result,").append(supplies - buy);
        String outputInfo = builder.toString();
        builder.setLength(0);

        return outputInfo;
    }
}
