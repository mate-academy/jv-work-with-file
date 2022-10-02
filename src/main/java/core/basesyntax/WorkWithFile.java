package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERARION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String [] dataFromFile = read(fromFileName);
        String report = createReport(dataFromFile);
        write(report, toFileName);
    }

    private String [] read(String string) {
        File fileRead = new File(string);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileRead));
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data file" + fileRead, e);
        }
        String readFile = builder.toString();
        String[] data = readFile.split(System.lineSeparator());
        return data;
    }

    private String createReport(String [] data) {
        int supply = 0;
        int buy = 0;
        for (String record : data) {
            String[] splittedRecord = record.split(",");
            if (splittedRecord[OPERARION_INDEX].equals("supply")) {
                supply = supply + Integer.parseInt(splittedRecord[AMOUNT_INDEX]);
            } else if (splittedRecord[OPERARION_INDEX].equals("buy")) {
                buy = buy + Integer.parseInt(splittedRecord[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        return new StringBuilder().append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator()).toString();
    }

    private void write(String report, String fromFile) {
        File fileWrite = new File(fromFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite,true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write");
        }
    }
}
