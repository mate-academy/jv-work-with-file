package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFile(fromFileName);
        String[] statistics = calculateStatistic(fileData);
        writeToFile(statistics, toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String[] calculateStatistic(String read) {
        int supply = 0;
        int buy = 0;
        String[] strings = read.split(System.lineSeparator());
        for (String str : strings) {
            String[] splited = str.split(",");
            if (splited[0].equals("supply")) {
                supply += Integer.parseInt(splited[1]);
            } else {
                buy += Integer.parseInt(splited[1]);
            }
        }
        StringBuilder resultBuilder = new StringBuilder();
        return new String[]{resultBuilder
                .append("supply").append(",")
                .append(supply).toString(), resultBuilder
                .append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(buy).toString(), resultBuilder
                .append(System.lineSeparator())
                .append("result").append(",")
                .append(supply - buy).toString()};
    }

    private void writeToFile(String[] reportStatistic, String toFileName) {
        for (String reportStatistics : reportStatistic) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(reportStatistics);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file " + toFileName, e);
            }
        }
    }
}
