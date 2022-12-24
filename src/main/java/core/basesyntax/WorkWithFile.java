package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] data = readFromFile(fromFileName);
        String report = countStatistic(data);
        writeToFile(report, toFileName);
    }

    private String [] readFromFile(String fromFileName) {
        StringBuilder stringBuilderReader = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilderReader.append(value).append(",");
                value = reader.readLine();
            }
            String[] fromStringBuilderReader = stringBuilderReader.toString().split(",");
            return fromStringBuilderReader;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
    }

    private String countStatistic(String [] data) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(SUPPLY)) {
                supply += Integer.parseInt(data[++i]);
            }
            if (data[i].equals(BUY)) {
                buy += Integer.parseInt(data[++i]);
            }
        }
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + (supply - buy);
    }

    private void writeToFile(String statistic, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write the file", e);
        }
    }
}
