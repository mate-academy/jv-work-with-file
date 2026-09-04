package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String FILE_SEPARATOR = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] data = readFile(fromFileName);
        String[][] report = createReport(data);
        writeToFile(report, toFileName);
    }

    private String[][] readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        String text = builder.toString();
        String[] rows = text.split(System.lineSeparator());
        String[][] data = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            data[i] = rows[i].split(FILE_SEPARATOR);
        }
        return data;
    }

    private String[][] createReport(String[][] data) {
        int supply = 0;
        int buy = 0;
        for (String[] datum : data) {
            if (datum[0].equals(SUPPLY)) {
                supply += Integer.parseInt(datum[1]);
            } else if (datum[0].equals(BUY)) {
                buy += Integer.parseInt(datum[1]);
            }
        }
        String[][] report = new String[3][2];
        String[] labels = {SUPPLY, BUY, RESULT};
        int[] values = {supply, buy, supply - buy};
        for (int i = 0; i < 3; i++) {
            report[i][0] = labels[i];
            report[i][1] = String.valueOf(values[i]);
        }
        return report;
    }

    private void writeToFile(String[][] report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] strings : report) {
                writer.write(strings[0] + "," + strings[1]);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
