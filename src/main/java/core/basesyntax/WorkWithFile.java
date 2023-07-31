package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String INDEX_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textFromFile = readFile(fromFileName);
        String report = createReport(textFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder readedFromFile = new StringBuilder();
        try (BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFile))) {
            int data = fromFileReader.read();
            while (data != -1) {
                readedFromFile.append((char) data);
                data = fromFileReader.read();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return readedFromFile.toString().split(INDEX_SEPARATOR);
    }

    private String createReport(String[] textFromFile) {
        int buy = 0;
        int supply = 0;
        for (String line : textFromFile) {
            if (line.split(",")[0].equals("buy")) {
                buy += Integer.parseInt(line.split(",")[1]);
            } else {
                supply += Integer.parseInt(line.split(",")[1]);
            }
        }
        return new StringBuilder().append("supply,").append(supply).append(INDEX_SEPARATOR)
                .append("buy,").append(buy).append(INDEX_SEPARATOR).append("result,")
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
