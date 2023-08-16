package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String receivingFile = readFromFile(fromFileName);
        String report = processedFile(receivingFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from the file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to this file" + toFileName, e);
        }
    }

    private String processedFile(String file) {
        StringBuilder builder1 = new StringBuilder();
        String[] data = file.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String element : data) {
            String[] elements = element.split(",");
            String nameOperation = elements[0];
            int quantity = Integer.parseInt(elements[1]);
            if (nameOperation.equals("buy")) {
                buy += quantity;
            } else {
                supply += quantity;
            }
        }
        int difference = supply - buy;
        builder1.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(difference);
        return builder1.toString();
    }
}

