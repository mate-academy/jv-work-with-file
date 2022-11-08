package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFromFile(fromFileName);
        String report = createReport(readData);
        writeData(report, toFileName);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file", e);
        }
        return builder.toString();
    }

    public String createReport(String string) {
        int buy = 0;
        int supply = 0;
        int result;
        String[] split = string.split(System.lineSeparator());
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("buy")) {
                buy += Integer.parseInt(split[i].substring(split[i].indexOf(",")
                        + INDEX_OF_NUMBER));
            } else if (split[i].startsWith("supply")) {
                supply += Integer.parseInt(split[i].substring(split[i].indexOf(",")
                        + INDEX_OF_NUMBER));
            }
        }
        result = supply - buy;
        String data = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator() + "result," + result;
        return data;
    }

    public void writeData(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to the file", e);
        }
    }
}
