package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int WORD_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    private static class Statistic {
        private int supplySum;
        private int buySum;
        private int result;

        public Statistic(int supplySum, int buySum, int result) {
            this.supplySum = supplySum;
            this.buySum = buySum;
            this.result = result;
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Statistic statistic = readStatistic(fromFileName);
        List<String> report = generateReport(statistic);
        writeReport(toFileName, report);
    }

    public Statistic readStatistic(String fromFileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fromFileName));
            return processLines(lines);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: ", e);
        }
    }

    private Statistic processLines(List<String> lines) {
        int supplySum = 0;
        int buySum = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            int extractedNumber = Integer.parseInt(parts[NUMBER_INDEX]);

            if (parts[WORD_INDEX].equals("supply")) {
                supplySum += extractedNumber;
            } else if (parts[WORD_INDEX].equals("buy")) {
                buySum += extractedNumber;
            }
        }

        int result = supplySum - buySum;
        return new Statistic(supplySum, buySum, result);
    }

    private List<String> generateReport(Statistic statistic) {
        List<String> report = new ArrayList<>();
        report.add("supply," + statistic.supplySum);
        report.add("buy," + statistic.buySum);
        report.add("result," + statistic.result);
        return report;
    }

    private void writeReport(String toFileName, List<String> report) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            for (String line : report) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while writing report to file: ", e);
        }
    }
}
