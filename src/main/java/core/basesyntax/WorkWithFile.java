package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readDataFromFile(fromFileName);
        String statistic = createReport(lines);
        writeDataToFile(toFileName, statistic);
    }

    public List<String> readDataFromFile(String fromFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.addAll(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return lines;
    }

    public void writeDataToFile(String fileName, String dataToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file: " + fileName, e);
        }
    }

    public String createReport(List<String> lines) {
        StringBuilder dataToWrite = new StringBuilder();

        int buy = 0;
        int supply = 0;
        for (int i = 0; i < lines.size(); i += 2) {
            if (lines.get(i).equals("supply")) {
                supply += Integer.parseInt(lines.get(i + 1));
            }
            if (lines.get(i).equals("buy")) {
                buy += Integer.parseInt(lines.get(i + 1));
            }
        }

        return dataToWrite.append("supply,").append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy)
                .toString();
    }
}
