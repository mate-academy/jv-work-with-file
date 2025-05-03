package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String contents = readFromFile(fromFileName);
        String report = getReport(contents);
        writeReport(report, toFileName);
    }

    public static String readFromFile(String file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(SEPARATOR);
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + file, e);
        }
    }

    public static String getReport(String contents) {
        int supply = 0;
        int buy = 0;
        String[] dataFromFile = contents.split(SEPARATOR);
        for (int i = 0; i < dataFromFile.length; i++) {
            String[] dataSplit = dataFromFile[i].split(DELIMITER);
            if (dataSplit[0].equals(SUPPLY)) {
                supply = supply + Integer.parseInt(dataSplit[1]);
            } else {
                buy = buy + Integer.parseInt(dataSplit[1]);
            }
        }
        int result = supply - buy;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(DELIMITER).append(supply).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buy).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(result);
        return stringBuilder.toString();
    }

    public static void writeReport(String str, String toFileName) {
        File newFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(str.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to the file: " + toFileName, e);
        }
    }
}
