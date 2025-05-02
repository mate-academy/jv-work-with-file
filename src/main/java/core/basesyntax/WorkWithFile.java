package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        String report = generateReport(lines);
        writeToFile(toFileName, report);
    }

    private List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fileName, e);
        }
        return lines;
    }

    private String generateReport(List<String> lines) {
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            String[] parse = line.split(COMMA);
            String name = parse[0];
            int value = Integer.parseInt(parse[1]);

            if (name.equals(SUPPLY)) {
                supply += value;
            } else if (name.equals(BUY)) {
                buy += value;
            }
        }

        int result = supply - buy;
        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }
}
