package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int LINE_KEY = 0;
    private static final int KEY_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        putReport(getReport(fromFileName), toFileName);
    }

    public String getReport(String fromFileName) {
        int supplying = 0;
        int buying = 0;
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String oneLine = reader.readLine();
            while (oneLine != null) {
                String[] line = oneLine.split(COMMA);
                if (line[LINE_KEY].equals("supply")) {
                    supplying += Integer.parseInt(line[KEY_VALUE]);
                } else {
                    buying += Integer.parseInt(line[KEY_VALUE]);
                }
                oneLine = reader.readLine();
            }
            return new StringBuilder()
                    .append("supply,").append(supplying).append(System.lineSeparator())
                    .append("buy,").append(buying).append(System.lineSeparator())
                    .append("result,").append(supplying - buying).toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    public void putReport(String fileReport, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}

