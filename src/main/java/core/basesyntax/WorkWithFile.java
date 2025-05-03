package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        List<String> lines = readFile(fromFileName);
        Report report = processTransactions(lines);
        writeReportToFile(report, toFileName);
    }

    private List<String> readFile(String fromFileName) throws IOException {
        return Files.readAllLines(Path.of(fromFileName));
    }

    private Report processTransactions(List<String> lines) {
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length < 2) {
                continue;
            }

            String operation = parts[0].trim();
            try {
                int amount = Integer.parseInt(parts[1].trim());
                if ("supply".equals(operation)) {
                    supply += amount;
                } else if ("buy".equals(operation)) {
                    buy += amount;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return new Report(supply, buy);
    }

    private void writeReportToFile(Report report, String toFileName) throws IOException {

        Files.write(Path.of(toFileName), new byte[0]);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write("supply," + report.getSupply());
            writer.newLine();
            writer.write("buy," + report.getBuy());
            writer.newLine();
            writer.write("result," + report.getResult());
            writer.newLine();
        }
    }

    private static class Report {
        private final int supply;
        private final int buy;

        public Report(int supply, int buy) {
            this.supply = supply;
            this.buy = buy;
        }

        public int getSupply() {
            return supply;
        }

        public int getBuy() {
            return buy;
        }

        public int getResult() {
            return supply - buy;
        }
    }
}
