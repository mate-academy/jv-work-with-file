package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        createNewFile(toFileName);
        writeReport(toFileName, readDataFromFile(fromFileName));
    }

    private String readDataFromFile(String fileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String stringLine = bufferedReader.readLine();
            while (stringLine != null) {
                if (stringLine.split(SEPARATOR)[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(stringLine.split(SEPARATOR)[1]);
                } else {
                    buy += Integer.parseInt(stringLine.split(SEPARATOR)[1]);
                }
                stringLine = bufferedReader.readLine();
            }
            return stringBuilder.append(SUPPLY).append(SEPARATOR).append(supply)
                    .append(System.lineSeparator())
                    .append(BUY).append(SEPARATOR).append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT).append(SEPARATOR).append(supply - buy)
                    .append(System.lineSeparator()).toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
    }

    private void writeReport(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileName, e);
        }
    }

    private void createNewFile(String fileName) {
        try {
            File fileReport = new File(fileName);
            fileReport.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file: " + fileName, e);
        }
    }
}
