package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        String report = getString(lines);
        writeStringToFile(toFileName, report);
    }

    private static String getString(List<String> lines) {
        int supply = 0;
        int buy = 0;

        for (String l : lines) {
            String[] data = l.split(",");
            if (data.length != 2) {
                continue;
            }
            String op = data[0].trim().toLowerCase();
            int amount = Integer.parseInt(data[1].trim());

            switch (op) {
                case SUPPLY:
                    supply += amount;
                    break;
                case BUY:
                    buy += amount;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + op);
            }
        }

        String ls = System.lineSeparator();
        StringBuilder sb = new StringBuilder(SUPPLY).append(",").append(supply).append(ls)
                .append(BUY).append(",").append(buy).append(ls)
                .append(RESULT).append(",").append(supply - buy).append(ls);
        return sb.toString();
    }

    private List<String> readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read from the file "
                    + fileName, e);
        }
        return lines;
    }

    private void writeStringToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Could not write to the file "
                    + fileName, e);
        }
    }
}
