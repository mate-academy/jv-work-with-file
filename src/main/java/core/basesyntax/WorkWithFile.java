package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private List<String> readLines(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName),
                StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return lines;
    }

    public enum Operation {
        SUPPLY("supply"),
        BUY("buy");

        private final String code;

        Operation(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    private static class Statistics {
        private final int supply;
        private final int buy;

        Statistics(int supply, int buy) {
            this.supply = supply;
            this.buy = buy;
        }

        public int getSupply() {
            return supply;
        }

        public int getBuy() {
            return buy;
        }
    }

    private Statistics calculateStatistics(List<String> lines) {
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                throw new RuntimeException("Invalid data format in line: " + line);
            }
            String operationCodeFromFile = parts[0].trim();
            try {
                int n = Integer.parseInt(parts[1].trim());
                Operation operationType = null;
                if (Operation.SUPPLY.getCode().equals(operationCodeFromFile)) {
                    operationType = Operation.SUPPLY;
                } else if (Operation.BUY.getCode().equals(operationCodeFromFile)) {
                    operationType = Operation.BUY;
                } else {
                    throw new RuntimeException("Unsupported operation: " + operationCodeFromFile);
                }
                switch (operationType) {
                    case SUPPLY:
                        supply += n;
                        break;
                    case BUY:
                        buy += n;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected operation type: "
                                + operationType);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in line: " + line, e);
            }
        }
        return new Statistics(supply, buy);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLines(fromFileName);
        Statistics stats = calculateStatistics(lines);
        String report = buildReport(stats.getSupply(), stats.getBuy());
        writeReport(toFileName, report);
    }

    private String buildReport(int supply, int buy) {
        StringBuilder sb = new StringBuilder();
        sb.append("supply,").append(supply).append(System.lineSeparator());
        sb.append("buy,").append(buy).append(System.lineSeparator());
        sb.append("result,").append(supply - buy);
        return sb.toString();
    }

    private void writeReport(String fileName, String report) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName),
                StandardCharsets.UTF_8)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + fileName, e);
        }
    }
}
