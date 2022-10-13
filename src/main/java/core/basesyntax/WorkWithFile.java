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

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeToFile(toFileName, report);
    }

    private String createReport(String fromFileName) {
        String dataFromFile = readFile(fromFileName);
        int supply = 0;
        int buy = 0;
        String[] splitByLineSeparator = dataFromFile.split(System.lineSeparator());
        for (String each : splitByLineSeparator) {
            String[] commaArray = each.split(",");
            if (commaArray[NAME_INDEX].equals("supply")) {
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
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeToFile(String toFileName, String resultOfCalculate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultOfCalculate);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
