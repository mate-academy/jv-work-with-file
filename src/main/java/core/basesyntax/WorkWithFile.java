package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SPLIT_LINE = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textFromFile = readFile(fromFileName);
        String report = createReport(textFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder readedFromFile = new StringBuilder();
        try (BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFile))) {
            readedFromFile.append(fromFileReader.readLine()).append(LINE_SEPARATOR);
            while (fromFileReader.ready()) {
                readedFromFile.append(fromFileReader.readLine()).append(LINE_SEPARATOR);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return readedFromFile.toString().split(LINE_SEPARATOR);
    }

    private String createReport(String[] textFromFile) {
        int buy = 0;
        int supply = 0;
        for (String line : textFromFile) {
            String[] record = line.split(SPLIT_LINE);
            if (record[OPERATION_INDEX].equals("buy")) {
                buy += Integer.parseInt(record[AMOUNT_INDEX]);
            } else {
                supply += Integer.parseInt(record[AMOUNT_INDEX]);
            }
        }
        return new StringBuilder().append("supply,").append(supply).append(LINE_SEPARATOR)
                .append("buy,").append(buy).append(LINE_SEPARATOR).append("result,")
                .append(supply - buy).toString();
    }

    private void writeToFile(String toFileName,String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter writeTo = new BufferedWriter(new FileWriter(toFile))) {
            writeTo.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }
}
