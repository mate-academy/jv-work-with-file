package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SYMBOL_SPLIT = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeData(toFileName, readFile(fromFileName));
    }

    private String readFile(String fileName) {
        StringBuilder fileRead = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                fileRead.append(value).append(SYMBOL_SPLIT);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return countAndCreateReport(fileRead);
    }

    private String countAndCreateReport(StringBuilder fileRead) {
        String[] readFromFile = fileRead.toString().split(SYMBOL_SPLIT);
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < readFromFile.length; i++) {
            if (readFromFile[i].equals(SUPPLY)) {
                supply += Integer.parseInt(readFromFile[i + 1]);
            } else if (readFromFile[i].equals(BUY)) {
                buy += Integer.parseInt(readFromFile[i + 1]);
            }
        }
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY).append(SYMBOL_SPLIT).append(supply)
                .append(System.lineSeparator()).append(BUY).append(SYMBOL_SPLIT)
                .append(buy).append(System.lineSeparator()).append("result")
                .append(SYMBOL_SPLIT).append(supply - buy).toString();
    }

    private void writeData(String filename, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(String.valueOf(data));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }
}
