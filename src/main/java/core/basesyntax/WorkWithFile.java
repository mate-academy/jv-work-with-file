package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int DATA_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        createFileReport(generateReport(readFile(fromFileName)), toFileName);
    }

    private List readFile(String fromFileName) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file", e);
        }
        return lines;
    }

    private String generateReport(List<String> lines) {
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            String[] values = line.split(SEPARATOR);
            switch (values[DATA_INDEX]) {
                case SUPPLY: {
                    supply += Integer.parseInt(values[VALUE_INDEX]);
                    break;
                }
                case BUY: {
                    buy += Integer.parseInt(values[VALUE_INDEX]);
                    break;
                }
                default: {
                    break;
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supply - buy)
                .append(System.lineSeparator());
        return report.toString();
    }

    private void createFileReport(String generatedReport, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(generatedReport);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }

}
