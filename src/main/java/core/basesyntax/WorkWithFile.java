package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();
    private static final Character COMASEPARATOR = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        String file = readFile(fromFileName);
        String result = countResult(file);
        writeToFile(toFileName, result);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String countResult(String value) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        String[] array = value.split(SEPARATOR);
        for (String iterator : array) {
            String[] valueArray = iterator.split(String.valueOf(COMASEPARATOR));
            if (valueArray[0].equals("supply")) {
                supply += Integer.parseInt(valueArray[1]);
            } else {
                buy += Integer.parseInt(valueArray[1]);
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(SEPARATOR);
        stringBuilder.append("buy,").append(buy).append(SEPARATOR);
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file" + toFileName, e);
        }
    }
}
