package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int LENGTH_OF_ARRAY = 2;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = ",";

    public String getStatistic(String fromFileName, String toFileName) {
        int[] statistics;

        statistics = parseInformationFromFile(fromFileName);
        String report = buildReport(statistics);

        writeReport(toFileName, report);

        return report;
    }

    private int[] parseInformationFromFile(String fromFileName) {
        int[] result = new int[LENGTH_OF_ARRAY];

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseLine(line, result);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }

        return result;
    }

    private void parseLine(String line, int[] result) {
        String[] parts = line.trim().split(SEPARATOR, 2);
        if (parts.length != 2) {
            throw new RuntimeException("Invalid line: " + line);
        }

        String operation = parts[0].trim();
        int value;
        try {
            value = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number in line: " + line, e);
        }

        switch (operation) {
            case SUPPLY -> result[SUPPLY_INDEX] += value;
            case BUY -> result[BUY_INDEX] += value;
            default -> throw new RuntimeException("Unknown operation: " + operation);
        }
    }

    private String buildReport(int[] statistics) {
        int result = statistics[SUPPLY_INDEX] - statistics[BUY_INDEX];
        return "supply," + statistics[SUPPLY_INDEX] + System.lineSeparator()
                + "buy," + statistics[BUY_INDEX] + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writeReport(String toFileName, String content) {
        try {
            Files.writeString(Path.of(toFileName), content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file: " + toFileName, e);
        }
    }
}
