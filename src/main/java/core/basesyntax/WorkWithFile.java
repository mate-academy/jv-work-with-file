package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] splitByLineSeparator = data.split(LINE_SEPARATOR);
        for (String each : splitByLineSeparator) {
            String[] commaArray = each.split(COMMA);
            if (commaArray[NAME_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(commaArray[VALUE_INDEX]);
            } else {
                buy += Integer.parseInt(commaArray[VALUE_INDEX]);
            }
        }
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return resultBuilder.toString();
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file from " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
