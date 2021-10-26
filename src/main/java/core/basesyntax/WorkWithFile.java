package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String readedFile = readFromFile(fromFileName);
        String statistic = calculateStatistic(readedFile);
        writeToFile(statistic, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String calculateStatistic(String data) {
        String[] splittedData = data.split(" ");
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (int i = 0; i < splittedData.length; i++) {
            int index = splittedData[i].indexOf(',');
            if (splittedData[i].contains("supply")) {
                supply += Integer.valueOf(splittedData[i].substring(index + 1));
            }
            if (splittedData[i].contains("buy")) {
                buy += Integer.valueOf(splittedData[i].substring(index + 1));
            }
        }
        result = supply - buy;
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("supply,").append(supply)
                        .append(System.lineSeparator()).append("buy,").append(buy)
                        .append(System.lineSeparator()).append("result,")
                        .append(result);
        return resultBuilder.toString();
    }

    private void writeToFile(String statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Cant't write to file", e);
        }
    }
}
