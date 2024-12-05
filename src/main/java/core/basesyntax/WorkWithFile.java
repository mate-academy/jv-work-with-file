package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String[] REPORT_COLUMNS = new String[] {"supply", "buy", "result"};

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        String report = getReportByLines(lines);
        writeReport(report, toFileName);
    }

    private void writeReport(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName + ". Error: " + e);
        }
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getReportByLines(List<String> lines) {
        HashMap<String, Integer> results = new HashMap<>();

        for (String line: lines) {
            String[] splitLine = line.split(COMMA);
            String operationType = splitLine[0];
            int amount = Integer.parseInt(splitLine[1]);

            Integer previousSum = results.get(operationType);

            if (previousSum != null) {
                results.replace(operationType, previousSum + amount);
            } else {
                results.put(operationType, amount);
            }
        }

        Integer totalSupply = results.get(REPORT_COLUMNS[0]);
        Integer totalBuy = results.get(REPORT_COLUMNS[1]);
        results.put(REPORT_COLUMNS[2], totalSupply - totalBuy);

        StringBuilder report = new StringBuilder();
        for (String column: REPORT_COLUMNS) {
            report
                    .append(column)
                    .append(COMMA)
                    .append(results.get(column))
                    .append(System.lineSeparator());
        }

        return report.toString();
    }
}
