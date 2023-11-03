package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX = 0;
    private static final int COUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String stats = summariseStats(data);
        writeToFile(stats, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                text.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from the file: " + fromFileName, e);
        }
        return text.toString();
    }

    private String summariseStats(String file) {
        StringBuilder report = new StringBuilder();
        String[] dataFromFile = file.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : dataFromFile) {
            String[] splitWords = line.split(COMMA);
            int amount = Integer.parseInt(splitWords[COUNT]);
            String operation = splitWords[INDEX];
            if (operation.equals(BUY_OPERATION)) {
                buy += amount;
            } else {
                supply += amount;
            }
        }
        int result = supply - buy;
        report.append(SUPPLY_OPERATION).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY_OPERATION).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return report.toString();
    }

    private void writeToFile(String formattedFile, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(formattedFile);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to this file" + toFileName, e);
        }
    }
}
