package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int REPORT_PARTS = 3; // {"supply", "buy", "result"}
    private static final String CSV_SEPARATOR = ",";
    private static final int SUPPLY_REPORT_INDEX = 0;
    private static final int BUY_REPORT_INDEX = 1;
    private static final int RESULT_REPORT_INDEX = 2;
    private static final int REPORT_PART_IN_LINE_INDEX = 0;
    private static final int RESULT_IN_LINE_INDEX = 1;
    private static final String REPORT_FORMAT = "supply,%d%nbuy,%d%nresult,%d";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readLinesFromFile(fromFileName);
        String report = processData(data);
        writeToFile(toFileName, report);
    }

    private String[] readLinesFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName)).toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Cannot read lines from file '%s'", fileName), e
            );
        }
    }

    private String processData(String[] data) {
        int[] results = new int[REPORT_PARTS];
        for (String line : data) {
            processLine(line, results);
        }
        results[RESULT_REPORT_INDEX] = results[SUPPLY_REPORT_INDEX] - results[BUY_REPORT_INDEX];
        return generateReport(results);
    }

    private void processLine(String line, int[] results) {
        String[] parts = line.split(CSV_SEPARATOR);
        switch (parts[REPORT_PART_IN_LINE_INDEX]) {
            case "supply":
                results[SUPPLY_REPORT_INDEX] += Integer.parseInt(parts[RESULT_IN_LINE_INDEX]);
                break;
            case "buy":
                results[BUY_REPORT_INDEX] += Integer.parseInt(parts[RESULT_IN_LINE_INDEX]);
                break;
            default:
                break;
        }
    }

    private String generateReport(int[] results) {
        return String.format(
                REPORT_FORMAT,
                results[SUPPLY_REPORT_INDEX],
                results[BUY_REPORT_INDEX],
                results[RESULT_REPORT_INDEX]
        );
    }

    private void writeToFile(String fileName, String data) {
        try {
            Files.write(Path.of(fileName), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Cannot write data to file '%s'", fileName), e
            );
        }
    }
}
