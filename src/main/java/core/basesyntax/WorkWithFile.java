package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int BEFORE = 0;
    private static final int AFTER = 1;

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "testReport");
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder finalData = new StringBuilder();
        String[] strings = readFile(fromFileName).split(System.lineSeparator());
        for (String string : strings) {
            if (string.split(SEPARATOR)[BEFORE].equals(SUPPLY)) {
                supply += Integer.parseInt(string.split(SEPARATOR)[AFTER]);
            }
            if (string.split(SEPARATOR)[BEFORE].equals(BUY)) {
                buy += Integer.parseInt(string.split(SEPARATOR)[AFTER]);
            }
        }
        finalData.append(SUPPLY).append(SEPARATOR).append(String.valueOf(supply))
                .append(System.lineSeparator()).append(BUY).append(SEPARATOR)
                .append(String.valueOf(buy)).append(System.lineSeparator()).append(RESULT)
                .append(SEPARATOR).append(String.valueOf(supply - buy));
        createReport(toFileName, finalData.toString());
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Can't read the file with the name: " + fromFileName);
        }
        return stringBuilder.toString();
    }

    private void createReport(String toFileName, String string) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}

