package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        Statistic statistic = readStatistic(fromFileName);
        String report = buildReport(statistic);
        writeToFile(toFileName, report);
    }

    private Statistic readStatistic(String fileName) {
        Statistic statistic = new Statistic();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(DELIMITER);
                if (parts.length < 2) {
                    continue;
                }

                String type = parts[0].trim();
                int value;

                try {
                    value = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number in line: " + line, e);
                }

                if (SUPPLY.equals(type)) {
                    statistic.addSupply(value);
                } else if (BUY.equals(type)) {
                    statistic.addBuy(value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file", e);
        }

        return statistic;
    }

    private String buildReport(Statistic statistic) {
        StringBuilder builder = new StringBuilder();

        builder.append(SUPPLY).append(DELIMITER)
                .append(statistic.getSupply()).append(System.lineSeparator())
                .append(BUY).append(DELIMITER)
                .append(statistic.getBuy()).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER)
                .append(statistic.getResult());

        return builder.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing file", e);
        }
    }

    private static class Statistic {
        private int supply;
        private int buy;

        void addSupply(int value) {
            supply += value;
        }

        void addBuy(int value) {
            buy += value;
        }

        int getSupply() {
            return supply;
        }

        int getBuy() {
            return buy;
        }

        int getResult() {
            return supply - buy;
        }
    }
}

