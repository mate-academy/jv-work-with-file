package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY = 0;
    private static final int BUY = 1;
    private static final int VALUE = 1;
    private static final int FIELDS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = new int[FIELDS];
        File inputFile = new File(fromFileName);
        FileReader fileReader;
        try {
            fileReader = new FileReader(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file!", e);
        }
        createReport(fileReader, data);
        File outputFile = new File(toFileName);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(outputFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
        writeToFile(fileWriter, data);
    }

    private void createReport(FileReader fileReader, int[] data) {
        String[] record;
        String recordString = "";
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            recordString = bufferedReader.readLine();
            while (recordString != null) {
                record = recordString.split(",");
                if (record[SUPPLY].equals("supply")) {
                    data[SUPPLY] += Integer.parseInt(record[VALUE]);
                } else {
                    data[BUY] += Integer.parseInt(record[VALUE]);
                }
                recordString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file!", e);
        }
    }

    private void writeToFile(FileWriter fileWriter, int[] data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(new StringBuilder("supply,")
                    .append(String.valueOf(data[SUPPLY]))
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(String.valueOf(data[BUY]))
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(String.valueOf(data[SUPPLY] - data[BUY]))
                    .toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
