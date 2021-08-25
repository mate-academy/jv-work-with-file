package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ZERO = 0;
    private static final int VALUE = 1;
    private static final int DATA = 100;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = new String[DATA];
        File inputFile = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            data[ZERO] = bufferedReader.readLine();
            int index = ZERO;
            while (data[index] != null) {
                index++;
                data[index] = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file!", e);
        }
        String report = createReport(data);
        File outputFile = new File(toFileName);
        writeToFile(outputFile, report);
    }

    private String createReport(String[] data) {
        String[] record;
        int supply = ZERO;
        int buy = ZERO;
        for (String datum : data) {
            if (datum == null) {
                break;
            }
            record = datum.split(",");
            if (record[ZERO].equals("supply")) {
                supply += Integer.parseInt(record[VALUE]);
            } else {
                buy += Integer.parseInt(record[VALUE]);
            }
        }
        return new StringBuilder("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).toString();
    }

    private void writeToFile(File file, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
