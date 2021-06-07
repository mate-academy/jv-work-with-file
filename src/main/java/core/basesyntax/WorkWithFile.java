package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File statistic = new File(fromFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(statistic))) {
            String record = reader.readLine();

            while (record != null) {
                int value = Integer.parseInt(record.substring(record.indexOf(CSV_SEPARATOR) + 1));
                if (record.contains(SUPPLY)) {
                    supply += value;
                } else {
                    buy += value;
                }
                record = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file " + fromFileName, e);
        }

        writeToFile(toFileName, createReport(supply, buy));
    }

    private String createReport(int supply, int buy) {
        StringBuilder report = new StringBuilder();
        String nextLine = System.lineSeparator();

        return report.append(SUPPLY).append(CSV_SEPARATOR).append(supply).append(nextLine)
                .append(BUY).append(CSV_SEPARATOR).append(buy).append(nextLine)
                .append(RESULT).append(CSV_SEPARATOR).append(supply - buy).toString();
    }

    private void writeToFile(String toFileName, String report) {
        File resultFile = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Could not write in file " + toFileName, e);
        }
    }
}
