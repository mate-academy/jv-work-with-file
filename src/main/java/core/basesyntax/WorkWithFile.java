package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String[] countedValues = dataFromFile.split(System.lineSeparator());
        for (String value: countedValues) {
            String[] values = value.split("[, ]");
            if (value.contains("supply")) {
                supply += Integer.parseInt(values[VALUE_INDEX]);
            } else {
                buy += Integer.parseInt(values[VALUE_INDEX]);
            }
        }
        result = supply - buy;
        return "supply," + supply
                + System.lineSeparator()
                + "buy," + buy
                + System.lineSeparator()
                + "result," + result;
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                builder.append(dataLine).append(System.lineSeparator());
                dataLine = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file " + fromFileName, e);
        }
    }
}
