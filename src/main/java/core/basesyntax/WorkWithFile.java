package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE = 0;
    private static final int VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        StringBuilder dataString = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String record = bufferedReader.readLine();
            while (record != null) {
                dataString.append(record).append(System.lineSeparator());
                record = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file!", e);
        }
        String[] data = dataString.toString().split(System.lineSeparator());
        String report = createReport(data);
        File outputFile = new File(toFileName);
        writeToFile(outputFile, report);
    }

    private String createReport(String[] data) {
        String[] record;
        int supply = 0;
        int buy = 0;
        for (String datum : data) {
            record = datum.split(",");
            if (record[TYPE].equals("supply")) {
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
