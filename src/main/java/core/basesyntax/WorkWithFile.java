package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int DATA = 0;
    private static final int COUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        data = generateReport(data);
        writeToFile(toFileName, data);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder bilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                bilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return bilder.toString();
    }

    private String generateReport(String data) {
        String[] strings = data.split(System.lineSeparator());
        int supData = 0;
        int buyData = 0;
        for (String value : strings) {
            String[] option = value.split(SEPARATOR);
            if (option[DATA].equals(SUPPLY)) {
                supData += Integer.valueOf(option[COUNT]);
            } else if (option[DATA].equals(BUY)) {
                buyData += Integer.valueOf(option[COUNT]);
            }
        }
        StringBuilder bilder = new StringBuilder();
        bilder.append(SUPPLY).append(SEPARATOR).append(supData)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buyData)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supData - buyData);
        return bilder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        File writeFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, false))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
