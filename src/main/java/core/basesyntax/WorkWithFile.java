package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";
    public static final String BUY = "buy";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        createFile(toFileName);
        String report = readFromFile(fromFileName);
        createReport(toFileName, report);
    }

    private void createFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a file.");
        }
    }

    private String readFromFile(String fileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.split(COMMA)[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(line.split(COMMA)[1]);
                } else {
                    buy += Integer.parseInt(line.split(COMMA)[1]);
                }
                line = reader.readLine();
            }
            builder.append(SUPPLY).append(COMMA).append(supply)
                    .append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT).append(COMMA).append(supply - buy)
                    .append(System.lineSeparator());
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    private void createReport(String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
