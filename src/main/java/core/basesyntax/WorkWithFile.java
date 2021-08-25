package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String ACTION_SUPPLY = "supply";
    private static final String ACTION_BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR_CSV_FILE = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFirstFile = readFromFile(fromFileName).split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : dataFromFirstFile) {
            String[] lineInfo = line.split(SEPARATOR_CSV_FILE);
            for (int i = 0; i < lineInfo.length - 1; i++) {
                if (lineInfo[i].equals(ACTION_SUPPLY)) {
                    supply += Integer.parseInt(lineInfo[i + 1]);
                }
                if (lineInfo[i].equals(ACTION_BUY)) {
                    buy += Integer.parseInt((lineInfo[i + 1]));
                }
            }
        }
        String dataToWrite = new StringBuilder(ACTION_SUPPLY).append(SEPARATOR_CSV_FILE)
                .append(supply).append(System.lineSeparator())
                .append(ACTION_BUY).append(SEPARATOR_CSV_FILE).append(buy)
                .append(System.lineSeparator()).append(RESULT)
                .append(SEPARATOR_CSV_FILE).append(supply - buy).toString();
        writeToFile(toFileName, dataToWrite);
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into file", e);
        }
    }

    private String readFromFile(String fileName) {
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
