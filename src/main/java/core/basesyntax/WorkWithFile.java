package core.basesyntax;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        int buy = 0;
        int supply = 0;

        for (String l : lines) {
            String[] data = l.split(",");
            if ("supply".equals(data[0])) {
                supply += Integer.parseInt(data[1]);
            }
            if ("buy".equals(data[0])) {
                buy += Integer.parseInt(data[1]);
            }
        }

        String lineSeparator = System.lineSeparator();
        String report = String.format(
                "supply,%s%s" +
                        "buy,%s%s" +
                        "result,%s",
                supply, lineSeparator,
                buy, lineSeparator,
                supply - buy
        );
        writeStringToFile(toFileName, report);
    }

    private List<String> readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read from the file", e);
        }
        return lines;
    }

    private void writeStringToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Could not write to the file", e);
        }
    }
}
