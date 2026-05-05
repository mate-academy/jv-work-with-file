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
    private static final String SEPARATOR = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        String report = createReport(lines);
        writeFile(toFileName, report);
    }

    private List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return lines;
    }

    private String createReport(List<String> lines) {
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            int amount = Integer.parseInt(parts[1]);
            if (parts[0].equals(SUPPLY)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        int result = supply - buy;
        return new StringBuilder()
                .append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result)
                .toString();
    }

    private void writeFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
