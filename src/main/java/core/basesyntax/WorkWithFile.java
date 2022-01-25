package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_CODENAME = "supply";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readData = readFromFile(fromFileName);
        String report = createReport(readData);
        writeToFile(report, toFileName);
    }

    private String createReport(String[] readData) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < readData.length; i += 2) {
            if (readData[i].equals(SUPPLY_CODENAME)) {
                supply += Integer.parseInt(readData[i + 1]);
            } else {
                buy += Integer.parseInt(readData[i + 1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't writer file " + toFileName, e);
        }
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value).append(SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        String sentence = stringBuilder.toString().toLowerCase();
        return sentence.split("\\W+");
    }
}
