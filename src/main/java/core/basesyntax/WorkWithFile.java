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
    private static final String REGEX = "\\W+";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFromFile(fromFileName);
        String report = createReport(content);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(String content) {
        int supply = 0;
        int buy = 0;
        String[] lines = content.split(REGEX);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals(SUPPLY)) {
                supply += Integer.parseInt(lines[i + 1]);
            } else if (lines[i].equals(BUY)) {
                buy += Integer.parseInt(lines[i + 1]);
            }
        }
        int result = supply - buy;
        return SUPPLY + COMMA + supply + System.lineSeparator()
                + BUY + COMMA + buy + System.lineSeparator()
                + RESULT + COMMA + result;
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
