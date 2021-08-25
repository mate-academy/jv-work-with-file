package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFirstFile = reader(fromFileName).split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line: dataFromFirstFile) {
            String[] lineInfo = line.split(CSV_SEPARATOR);
            for (int i = 0; i < lineInfo.length - 1; i++) {
                if (lineInfo[i].equals(SUPPLY)) {
                    supply += Integer.parseInt(lineInfo[i + 1]);
                }
                if (lineInfo[i].equals(BUY)) {
                    buy += Integer.parseInt((lineInfo[i + 1]));
                }
            }
        }
        String dataToWrite = new StringBuilder(SUPPLY).append(CSV_SEPARATOR)
                .append(supply).append(System.lineSeparator())
                .append(BUY).append(CSV_SEPARATOR).append(buy)
                .append(System.lineSeparator()).append(RESULT)
                .append(CSV_SEPARATOR).append(supply - buy).toString();
        writer(toFileName,dataToWrite);
    }

    private void writer(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into file", e);
        }
    }

    private String reader(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }
}
