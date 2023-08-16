package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String file = readFile(fromFileName);
        String result = countResult(file);
        writeToFile(toFileName, result);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file", e);
        }
        return stringBuilder.toString();
    }

    private String countResult(String value) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        String[] array = value.split(System.lineSeparator());
        for (String iterator : array) {
            String[] valueArray = iterator.split(",");
            if (valueArray[0].equals("supply")) {
                supply += Integer.parseInt(String.valueOf(valueArray[1]));
            } else {
                buy += Integer.parseInt(String.valueOf(valueArray[1]));
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buy).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, false))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file", e);
        }
    }
}
