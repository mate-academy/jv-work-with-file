package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String read) {
        StringBuilder temporary = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(read))) {
            String value = reader.readLine();
            while (value != null) {
                temporary.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + read, e);
        }
        return temporary.toString();
    }

    private String createReport(String data) {
        StringBuilder stringBuilder1 = new StringBuilder();
        String[] lines = data.split(" ");
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] operation = line.split(COMA);
            if (operation[OPERATION_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(operation[AMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(operation[AMOUNT_INDEX]);
            }
        }
        stringBuilder1
                .append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator());
        return stringBuilder1.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
