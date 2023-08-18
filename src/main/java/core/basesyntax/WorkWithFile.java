package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String receivingFile = readFromFile(fromFileName);
        String report = calculateReport(receivingFile);
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

    private String calculateReport(String fileContents) {
        StringBuilder builder1 = new StringBuilder();
        String[] data = fileContents.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String element : data) {
            String[] elements = element.split(COMMA);
            String nameOperation = elements[0];
            int quantity = Integer.parseInt(elements[1]);
            if (nameOperation.equals(BUY)) {
                buy += quantity;
            } else {
                supply += quantity;
            }
        }
        int difference = supply - buy;
        builder1.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(difference);
        return builder1.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to this file" + toFileName, e);
        }
    }
}
